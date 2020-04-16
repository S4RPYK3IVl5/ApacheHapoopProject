import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.{Dataset, Encoder, Encoders, SQLContext}

object Main {
  def main(args: Array[String]): Unit = {

    val sc = new SparkContext
    val sqlContext = new HiveContext(sc)
    import sqlContext.implicits._
    import org.apache.spark.sql.expressions.Window
    import org.apache.spark.sql.functions

    val events_df = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "false")
      .load("/flume/events/2010/01/*")
      .toDF("name", "price", "category", "ip", "date")
//    events_df.show()
    events_df.cache()

    // 1 task
    val top_category = events_df.groupBy("category")
      .count()
      .select("category", "count")
      .orderBy($"count".desc)
//    top_product.show(10)

    // 2task
    val window = Window.partitionBy("category").orderBy($"count".desc)
    val top_product = events_df.groupBy("category","name")
      .count()
      .withColumn("rank", functions.rank().over(window).alias("rank"))
      .select("category", "name", "count", "rank")
      .filter("rank <= 10")
    top_product.show(100)

  }
}
