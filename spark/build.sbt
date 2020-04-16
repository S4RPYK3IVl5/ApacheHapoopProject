name := "spark"

version := "0.1"

scalaVersion := "2.10.7"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.6.0"
libraryDependencies += "org.apache.spark" %% "spark-hive" % "1.6.0" % "provided"
