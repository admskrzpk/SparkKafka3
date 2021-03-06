ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.15"

lazy val root = (project in file("."))
  .settings(
    name := "SparkKafka3"
  )
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.1.3"
libraryDependencies  += "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.1.3"