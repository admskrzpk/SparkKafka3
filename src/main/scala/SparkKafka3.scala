import org.apache.spark.sql.functions.{col, upper}

object SparkKafka3 extends App {

  import org.apache.spark.sql.SparkSession

  val spark = SparkSession
    .builder()
    .master("local[*]")
    .getOrCreate()

  val message = spark
    .readStream
    .format("kafka")
    .option("subscribe", s"${args(0)}")
    .option("kafka.bootstrap.servers", ":9092")
    .option("includeHeaders", "true")
    .load

  val upperMessage = message
    .withColumn("value", upper((col("value")).cast("String")))

  val finalMessage = upperMessage
    .writeStream
    .format("kafka")
    .option("topic", s"${args(1)}")
    .option("checkpointLocation", "checkpoint")
    .option("kafka.bootstrap.servers", ":9092")
    .start()
    .awaitTermination
}
