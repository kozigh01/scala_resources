// vals for cross compiling feature: see core/target for both compiled artifacts
val scala310 = "3.1.0"
val scala320 = "3.2.0"

ThisBuild / scalaVersion := "3.2.0"
ThisBuild / version := "1.0.0"
ThisBuild / organization := "com.mkozi"

//  add external resolver for  downloading dependencies
// resolvers += Resolver.url("my-test-repo", url("https://rockthejvm.com/repository/..."))

// add Maven local repo
// resolvers += Resolver.mavenLocal

// custom tasks
//    can run from sbt repl - `sbt:root> printerTask`
lazy val printerTask = taskKey[Unit]("Custom Printer task")  // register task under name "Customer Printer task"
printerTask := {  // binding code to the registered task
  val uuidTask = uuidStringTask.value
  println(s"Generated uuid from task: $uuidTask")

  val uuidSetting = uuidStringSetting.value
  println(s"Generated uuid from setting: $uuidSetting")

  CustomTaskPrinter.print()
}

lazy val uuidStringTask = taskKey[String]("Random UUID generator")
uuidStringTask := {
  StringTask.strTask()
}

// custom settings
lazy val uuidStringSetting = settingKey[String]("Random UUID setting")
uuidStringSetting := {  // bing setting to code
  val uuid = StringTask.strTask()
  // additional code can go here
  uuid
}

// command aliases
addCommandAlias("ci", "compile;test;assembly")


lazy val core = (project in file("core"))
// lazy val core = (project in file("projects/playlists/sbt/core"))
  .settings(
    name := "Core",
    assembly / mainClass := Option("com.mkozi.core.CoreApp"),
    crossScalaVersions := List(scala310, scala320)  // cross compile: `sbt:root>+compile`
  )
lazy val server = (project in file("server"))
  .dependsOn(core)
  .settings(  
    name := "Server",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.13" % Test
    )
  )

lazy val root = (project in file("."))
  .aggregate(core, server)
  .dependsOn(core)  // this is needed to use code from core in root

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "fansi" % "0.4.0",
  "org.scalatest" %% "scalatest" % "3.2.13" % Test
)