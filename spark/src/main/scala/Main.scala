import org.apache.spark.SparkContext
import org.apache.spark.sql.{Dataset, Encoder, Encoders, SQLContext}

object Main {
  def main(args: Array[String]): Unit = {

//    case class Event(name: String, price: String, date: Long, category: String, ip: String)
//    implicit val enc: Encoder[Event] = Encoders.javaSerialization

    val sc = new SparkContext
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val events_df = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "false")
      .load("/flume/events/2010/01/*")
      .toDF("name", "price", "category", "ip", "date")
    events_df.show()

    val top_product = events_df.groupBy("category")
      .count()
      .select("category", "count")
      .orderBy($"count".desc)
    top_product.show()



//    val events_ds : Dataset[Event] = events_df.as[Event]
//    events_ds.show(162)

  }
}
