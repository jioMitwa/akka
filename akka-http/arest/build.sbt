name := "arest"

version := "1.0"

scalaVersion := "2.11.7"
  val akkaV       = "2.4.7"


EclipseKeys.withSource := true

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaV,
  "com.typesafe.akka" %% "akka-http-core" % akkaV,
  "com.typesafe.akka" %% "akka-http-experimental" % akkaV,
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaV,
  "com.typesafe.akka" %% "akka-stream" % akkaV)