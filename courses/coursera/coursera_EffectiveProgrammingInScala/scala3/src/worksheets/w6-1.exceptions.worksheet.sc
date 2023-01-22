def attemptSomething(): Unit =
  println("so far, so good")
  println("still there")
  throw RuntimeException("We can't continue")
  println("you will never see this")


val i = "3".toInt

attemptSomething()

import scala.util.Try
val tryInt: Try[Int] = Left(":sadface:")

import scala.io.Source
def lineStrings(fileName: String): Try[List[String]] =
  Try(Source.fromFile(fileName)).map(_.getLines().toList)