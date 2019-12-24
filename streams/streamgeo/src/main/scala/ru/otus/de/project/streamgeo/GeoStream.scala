package ru.otus.de.project.streamgeo

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{from_json, window}


object GeoStream extends App {

  val appName = "stream-geo"
  val kafkaTopic = "geo-service"
  val kafkaBootstrapServers = "http://35.238.167.27:9092"

  val spark = SparkSession.builder()
    .master("local[2]")
    .config("spark.ui.port", "4041")
    .appName(appName).getOrCreate()

  Logger.getLogger("org").setLevel(Level.OFF)

  val inputStream = spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", kafkaBootstrapServers)
    .option("subscribe", kafkaTopic)
    .option("startingOffsets", "earliest")
    .load()

  val geoSchema = new StructType()
    .add(StructField("number", StringType))
    .add(StructField("city", StringType))
    .add(StructField("datetime", StringType))
    .add(StructField("lat", StringType))
    .add(StructField("lng", StringType))

  import spark.implicits._
  val transformedStream: DataFrame = inputStream
    .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)").as[(String, String)]
    .select(from_json($"value", geoSchema).as("geo"))
    .selectExpr("geo.city", "TO_TIMESTAMP(geo.datetime, 'dd.MM.yyyy HH:mm:ss') AS timestamp")
    .withWatermark("timestamp", "20 seconds")
    .groupBy(
      window($"timestamp", "20 seconds", "10 seconds")
        .alias("load_dt"), $"city").count()

  transformedStream.writeStream
    .format("console")
//    .format("csv")
//    .outputMode("append")
//    .option("path", "./geo_stream_result/")
//    .partitionBy("load_dt")
//    .option("checkpointLocation", "./checkpoint/")
    .start()

  spark.streams.awaitAnyTermination()
}
