ThisBuild / scalaVersion := "3.2.1"
ThisBuild / version := "1.0.0"
ThisBuild / organization := "com.mkozi"

lazy val root = project
  .in(file("."))
  .settings(
    name := "udemy_ScalaAndFunctionalProgrammingEssentials",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
