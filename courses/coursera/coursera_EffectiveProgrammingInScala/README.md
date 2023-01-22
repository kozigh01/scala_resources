# Effective Programming in Scala

Course: [coursera](https://www.coursera.org/learn/effective-scala/home/week1)

## Resources
* My repo readme: [wsl_Setup](https://github.com/kozigh01/wsl_Setup/edit/main/README.md)
* [Scala](https://www.scala-lang.org/)j
  * [Install Scala](https://www.scala-lang.org/download/)
  * Install specific release: [releases](https://www.scala-lang.org/download/all.html)
  * [scala library](https://www.scala-lang.org/files/archive/api/current/)
  * Scaladex: [package library](https://index.scala-lang.org/)
    * [Fansi](https://index.scala-lang.org/com-lihaoyi/fansi)
    * [munit](https://index.scala-lang.org/scalameta/munit)
    * plugins:
      * [sbt-errors-summary](https://index.scala-lang.org/duhemm/sbt-errors-summary)
      * [sbt-site](https://index.scala-lang.org/sbt/sbt-sitel)
* [Coursier](https://get-coursier.io/)
* [sbt](https://scala-sbt.org)  
    * [coursera](https://www.coursera.org/learn/effective-scala/lecture/K0PFm/introduction-to-the-sbt-build-tool)
* Testing:
  * [MUnit](https://scalameta.org/munit/)
  * [MUnit ScalaCheck](https://scalameta.org/munit/docs/integrations/scalacheck.html)
  * [ScalaMock](https://scalamock.org/)
  * [scoverage](https://github.com/scoverage/sbt-scoverage) plugim

## Setup for WSL2
* Coursier: [cli-installation](https://get-coursier.io/docs/cli-installation)
  * If a JVM other than default is desired:
    ```bash
    $ cs setup -jvm 11
    ```
  * Install Coursier: [install instructions](https://get-coursier.io/docs/cli-installation#linux)
    ```bash
    $ curl -fL https://github.com/coursier/launchers/raw/master/cs-x86_64-pc-linux.gz | gzip -d > cs
    $ chmod +x c
    ```
  * After installing Coursier, run setup command:
    ```bash
    # standard install
    $ ./cs setup

    # install specific version
    $ cs install scala:3.2.0 && cs install scalac:3.2.0.
    ```
* Clone repo for course from github
* In repo directory, open VS Code  `$ code .`

## Imports
link: [coursera](https://www.coursera.org/learn/effective-scala/lecture/ijKTN/organize-code)
exaples (from hypothetical file: src/main/scala/effective/example/Hello.scala):
  ```scala
  import effective.example.Hello // imports just Hello
  import effective.example.Hello.foo  // imports just foo
  import effective.example.{Hello, Bar} // imports both Hello and bar
  import effective.example.* // scala3: imports everything from package effetive.example
  import effective.example._ // scala2: imports everything from package effetive.example
  ```