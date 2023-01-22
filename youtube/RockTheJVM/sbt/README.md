# Scala Training based on Youtube RockTheJVM Channel

* [github](https://github.com/kozigh01/scala_rtjvm_training)

## Resources
* [Scala language site](https://www.scala-lang.org/) | [Scala Install](https://www.scala-lang.org/download/)

## Videos / Playlists
### Playlists
#### SBT  
* [SBT Playlist](https://www.youtube.com/watch?v=itiL7QT2WkI&list=PLmtsMNDRU0BwinL745E_cv1PDeR_jhlMI)
* RockTheJVM blogs:
  * [An Introduction to SBT](https://blog.rockthejvm.com/sbt-tutorial/)
* make sure to change directory to: projects/playlists/sbt

* Basic Setup
  * Create file names build.sbt in folder root
  * Run: `$ sbt` in root of folder (projects/playlists/sbt)
    * this will setup the basic stucture for the project
  * Create source file directories (relative to root):
    ```bash
    $ mkdir -p src/main/scala
    $ mkdir -p src/test/scala
    ```
  * Create basic scala module:
    * create a package in new source directory:
      ```bash
      $ cd src/main/scala
      $ mkdir -p com/mkozi
      $ cd com/mkozi
      $ touch Main.scala
      ```
    * edit Main.scala to add some functionality
    * from root directory run `$ sbt`
      * in the sbt repl run `sbt> compile`
        * this will create the compiled files in the target directory
      * run the program with fully qualified class: `sbt> runMain com.mkozi.Main`
      * can start auto compile with: `sbt> ~compile`
    * add external dependency to main project
      * add following to build.sbt:
        ```
        libraryDependencies += "com.lihaoyi" %% "fansi" % "0.4.0"

          -- or --

        libraryDependencies ++= Seq(
          "com.lihaoyi" %% "fansi" % "0.4.0"
        )
        ```
      * update Main package to use
      * in root directory run `$ sbt`
      * compile project: `sbt> compile`
      * update com.mkozi.Main to use fansi then: `sbt> runMain com.mkozi.Main'
    * write test for the project:
      * update library dependencies in built.sbt by adding:
        ```
        "org.scalatest" %% "scalatest" % "3.2.13" % Test
        ```
      * create test file in src/test/scala
      * in sbt repl compile `sbt> compile`
      * run the test: `sbt> Test / testOnly com.mkozi.SimpleTest`
      * can run tests that match a regex: `sbt> Test / testOnly *Test`
      * test all the tests: `sbt> test`
* Part 2 (including multi-module setup)
  * multi-module projects
    * edit the build.sbt file
      * scope the main variables to the "ThisBuild" scope (prepend "ThisBuild / ")
      * add lazy vals for each module
      * run `sbt`
    * can add build.sbt files in each individual module or can add ".settings()" to module val in the main build.sbt - the run `$ sbt` at root
    * can switch to specific module in sbt repl with `sbt> project core'
  * project directory files:
    * can create files in the "project" directory which are automatically available from all built.sbt files 
  * Plugins
    * add file plugins.sbt in the "project" directory
    * edit plugins.sbt to add plugins:
      ```
      addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "1.2.0")
      ```
    * edit a build.sbt to use the plugin:
      ```
      lazy val core = (project in file("projects/core"))
        .settings(
          assembly / mainClass := Option("com.mkozi.core.CoreApp"),
          name := "Core"
        )
      ```
    * start sbt and run:
      ```bash
      sbt> project core

      # this will run the "assembly" project and build a .jar file for this project
      sbt> assembly
      ```
    * can run the jar with `java -jar target/scala-3.2.0/root-assembly-1.0.0.ja`
    * can define plugins globally for machine
* Part 3: resolvers, custom tasks, cross-compiling
  * resolvers:

