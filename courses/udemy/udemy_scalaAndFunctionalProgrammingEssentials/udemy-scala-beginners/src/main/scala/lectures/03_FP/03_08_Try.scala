package lectures.`03_FP`

import scala.util.{ Success, Failure, Try }
import scala.util.Random

object TryErrorHandling:
  def tryBasics =
    val success1 = Success(3)
    val failure1 = Failure(RuntimeException("dang, this failed"))
    
    println(s"success1: $success1")
    println(s"failure1: $failure1")

    def unsafeMethod() = throw RuntimeException("unsafe, unsafe ...")

    val result1 = Try[String](unsafeMethod())
    println(s"result1: $result1")

    // syntactic sugar
    val result2 = Try {
      // some code that might fail
      val x = 42
      val y = 3
      x + y
    }
    println(s"result2: $result2")

  def tryUtilities =
    val success1 = Success(3)
    val failure1 = Failure(RuntimeException("dang, this failed"))

    println(s"success1.isSuccess: ${success1.isSuccess}")
    println(s"success1.isFailure: ${success1.isFailure}")
    println(s"failure1.isSuccess: ${failure1.isSuccess}")
    println(s"failure1.isFailure: ${failure1.isFailure}")

    // orElse
    def unsafeMethod() = throw RuntimeException("unsafe, unsafe ...")
    def backupMethod() = "A valid result"
    def backupMethodFail() = throw RuntimeException("backup was no good...")
    var result1 = Try(unsafeMethod()).orElse(Try(backupMethod()))
    var result2 = Try(unsafeMethod()).orElse(Try(backupMethodFail()))
    println(s"result1: $result1")
    println(s"result2: $result2")

    // If you design the API - wrap results that can have errors in Try
    def betterUnsafeMethod(): Try[String] = Try(unsafeMethod())
    def betterBackupMethod(): Try[String] = Try(backupMethod())
    val betterResult1: Try[String] = betterUnsafeMethod().orElse(betterBackupMethod())
    println(s"betterResult1: $betterResult1")

  def tryMapFlatmapFilter =
    val success1 = Success(3)
    val failure1: Try[Int] = Failure(RuntimeException("dang, this failed"))

    println(s"success1.map(_ * 2): ${success1.map(_ * 2)}")
    println(s"success1.flatMap(x => Try(x * 10)): ${success1.flatMap(x => Try(x * 10))}")
    println(s"failure1.map(_ * 2): ${failure1.map(_ * 2)}")
    println(s"failure1.flatMap(x => Try(x * 2)): ${failure1.flatMap(x => Try(x * 2))}")
    println(s"success1.filter(_ > 10): ${success1.filter(_ > 10)}")

  def tryExercise1 =
    val hostname = "localhost"
    val port = "8080"
    def renderHTML(page: String) = println(page)
    def renderHTMLSafe(page: String): Try[Unit] = Try(println(page))

    class Connection:
      def get(url: String): String =
        val random = Random(System.nanoTime())
        random.nextBoolean match
          case true => ("<html>....</html>")
          case false => throw new RuntimeException("connection interrupted")
      
      def getSafe(url: String): Try[String] = Try(get(url))
      
    object HttpService:
      val random = Random(System.nanoTime())

      def getConnection(host: String, port: String): Connection =
        random.nextBoolean() match
          case true => Connection()
          case false => throw new RuntimeException("someone took the port")
      def safeGetConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))

    Try(HttpService.getConnection(hostname, port))
      .flatMap(conn => Try(conn.get("http://blah.com")))
      .foreach(println)  
    println("after the page print attempt")

    for
      conn <- Try(HttpService.getConnection(hostname, port))
      content <- Try(conn.get("url"))
    do renderHTML(content)
    println("afrter for comprehension render")

    val content = HttpService.safeGetConnection(hostname, port)
      .flatMap(conn => conn.getSafe("a url"))
      .flatMap(page => renderHTMLSafe(page))
    content.fold(
        ex => println(s"error: $ex"),
        v => println("it all worked, woohoo!")
      )
    content.failed.foreach(err => println(s"there was an error: $err"))
      // content.foreach(content => renderHTML(content))
      // content match
      //   case Success(value) => renderHTML(value)
      //   case Failure(exception) => println(exception)

@main def TryErrorHandlingMain =
  // TryErrorHandling.tryBasics
  // TryErrorHandling.tryUtilities
// TryErrorHandling.tryMapFlatmapFilter
TryErrorHandling.tryExercise1
