val scala3Version = "3.2.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "org.scalameta" %% "munit-scalacheck" % "0.7.29" % Test,
      "com.lihaoyi" %% "fansi" % "0.4.0"
    ),

    makeSite / mappings := {
      val indexFile = target.value / "index.html"
      IO.write(indexFile, "<h1>Hello, sbt!</h1>")
      Seq(indexFile -> "index.html")
    }
  )
