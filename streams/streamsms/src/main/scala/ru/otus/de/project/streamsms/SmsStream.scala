package ru.otus.de.project.streamsms

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{from_json, window}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object SmsStream extends App {
  val appName = "stream-sms"
  val kafkaTopic = "sms-valid"
  val kafkaBootstrapServers = "127.0.0.1:9092"

  val spark = SparkSession.builder()
    .master("local[2]")
    .config("spark.ui.port", "4042")
    .appName(appName).getOrCreate()
  Logger.getLogger("org").setLevel(Level.OFF)

  val inputStream = spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", kafkaBootstrapServers)
    .option("subscribe", kafkaTopic)
    .option("startingOffsets", "earliest")
    .load()

  val smsSchema = new StructType()
    .add(StructField("number", StringType))
    .add(StructField("city", StringType))
    .add(StructField("datetime", StringType))
    .add(StructField("sms", StringType))

  import spark.implicits._
  val transformedStream: DataFrame = inputStream
    .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)").as[(String, String)]
    .select(from_json($"value", smsSchema).as("sms"))
    .selectExpr("sms.city", "TO_TIMESTAMP(sms.datetime, 'dd.MM.yyyy HH:mm:ss') AS timestamp")
    .withWatermark("timestamp", "20 seconds")
    .groupBy(
      window($"timestamp", "20 seconds", "10 seconds")
        .alias("load_dt"), $"city").count()

  transformedStream.writeStream
    .format("delta")
    .outputMode("append")
    .option("path", "./sms_stream_result/")
    .option("checkpointLocation", "./checkpoint_sms/")
    .start()


  spark.streams.awaitAnyTermination()
}
