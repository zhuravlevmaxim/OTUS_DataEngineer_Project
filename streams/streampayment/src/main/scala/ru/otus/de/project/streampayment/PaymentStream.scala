package ru.otus.de.project.streampayment

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{from_json, window}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object PaymentStream extends App {
  val appName = "stream-payment"
  val kafkaTopic = "payment-valid"
  val kafkaBootstrapServers = "127.0.0.1:9092"

  val spark = SparkSession.builder()
    .master("local[2]")
    .config("spark.ui.port", "4043")
    .appName(appName).getOrCreate()
  Logger.getLogger("org").setLevel(Level.OFF)

  val inputStream = spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", kafkaBootstrapServers)
    .option("subscribe", kafkaTopic)
    .option("startingOffsets", "earliest")
    .load()

  val paymentSchema = new StructType()
    .add(StructField("number", StringType))
    .add(StructField("city", StringType))
    .add(StructField("datetime", StringType))
    .add(StructField("payment", StringType))

  import spark.implicits._
  val transformedStream: DataFrame = inputStream
    .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)").as[(String, String)]
    .select(from_json($"value", paymentSchema).as("payment"))
    .selectExpr(
      "payment.city",
      "TO_TIMESTAMP(payment.datetime, 'dd.MM.yyyy HH:mm:ss') AS timestamp",
      "CAST(payment.payment AS DECIMAL(28, 18)) AS payment"
    )
    .withWatermark("timestamp", "20 seconds")
    .groupBy(
      window($"timestamp", "20 seconds", "10 seconds")
        .alias("load_dt"), $"city").count()

  transformedStream.writeStream
    .format("delta")
    .outputMode("append")
    .option("path", "./payment_stream_result/")
    .option("checkpointLocation", "./checkpoint_payment/")
    .start()

  spark.streams.awaitAnyTermination()
}
