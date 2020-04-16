import java.util.Properties

import org.apache.spark.SparkContext
import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.sql.hive.HiveContext

object MainTop {
  def main(args: Array[String]): Unit = {

    val sc = new SparkContext
    val sqlContext = new HiveContext(sc)
    import org.apache.spark.sql.expressions.Window
    import org.apache.spark.sql.functions
    import sqlContext.implicits._

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

    // 2 task
    val window = Window.partitionBy("category").orderBy($"count".desc)
    val top_product = events_df.groupBy("category","name")
      .count()
      .withColumn("rank", functions.rank().over(window).alias("rank"))
      .select("category", "name", "count", "rank")
      .filter("rank <= 10")
      .orderBy("category", "count")
      .select("category", "name", "count")
//    top_product.show(100)

////////////////////////////////////////////////////////////////////////////////////////////

    val prop = new Properties()
    prop.put("user", "asaprykin")
    prop.setProperty("driver", "com.mysql.jdbc.Driver")
    val url = "jdbc:mysql://ip-10-0-0-21.us-west-1.compute.internal:3306/asaprykin"

    top_category.write.mode(SaveMode.Overwrite).jdbc(url, "top_category_mysql", prop)
    top_product.write.mode(SaveMode.Overwrite).jdbc(url, "top_product_mysql",prop)

  }
}
