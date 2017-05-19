name := """delta"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaWs,
  "com.google.inject" % "guice" % "4.0-beta"
)

libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "com.fasterxml.jackson.core" % "jackson-core" % "2.4.0",
    "com.fasterxml.jackson.core" % "jackson-annotations" % "2.5.0",
    "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.0"
)

libraryDependencies ++= Seq(
  "com.google.guava" % "guava" % "16.0",
  javaJdbc,
  cache,
  javaJpa,
  "org.apache.directory.api" % "api-all" % "1.0.0-M14",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "org.hibernate" % "hibernate-core" % "4.2.3.Final",
  "org.hibernate" % "hibernate-entitymanager" % "4.2.3.Final"
)     

 watchSources := (watchSources.value
      --- baseDirectory.value / "public"     ** "*").get