import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext


object MainJoin {
  def main(args: Array[String]): Unit = {

    val sc = new SparkContext
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val country_block_df = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .load("/flume/data/block")
//    country_block_df.show()

    val country_location_df = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .load("/flume/data/location")
//    country_location_df.show()

    val joined_country_with_ip = country_block_df.join(country_location_df, Seq("geoname_id"), "inner")
    .select("geoname_id", "network", "country_name")

    val events_df = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "false")
      .load("/flume/events/2010/01/*")
      .toDF("name", "price", "category", "network", "date")
    //    events_df.show()

    val joined_event_with_country = events_df
      .join(joined_country_with_ip, Seq("network"), "inner")
      .select("country_name", "price")
//    joined_event_with_country.show()

    val top_country = joined_event_with_country
      .selectExpr("cast(price as int) price", "cast(country_name as string) country_name")
      .groupBy("country_name")
      .sum("price")
      .orderBy($"sum(price)".desc)
    top_country.show(10)

  }
}
