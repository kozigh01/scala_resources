package exceptions_w6_01

import scala.util.control.NonFatal
import scala.util.{ Try, Success, Failure, Using }

import java.time.{ LocalDate, Period }
import scala.io.Source

object w6_01_tryCatch:
  def firstError(): Unit =
    println("so far, so good")
    println("still there")
    throw RuntimeException("We can't continue")
    println("you will never see this")

  def tryCatchExample(): Unit =
    val data = 
      try
        // val x = 10/0

        val s: String = null
        s.toLowerCase()

        w6_01_tryCatch.firstError()
      catch
        case ex: ArithmeticException =>
          System.err.println(s"Something went wrong: $ex")
          println("Stopping the program")
          -2
        case ex: RuntimeException =>
          System.err.println(s"Something went wrong: $ex")
          println("Stopping the program")
          -11
        case NonFatal(throwable) =>
          println(s"something non-fatal happened: $throwable")
          -3
      finally
        println("running finally clause")

    println(s"data: $data")

object w6_01_tryType:
  def tryTypeExampleFail(): Try[Int] =
    Try {
      println("\nso far, so good")
      println("still there")
      val i = 1/0
      // throw RuntimeException("We can't continue")
      println("you will never see this")

      42
    }

  def tryTypeExampleSuccess(): Try[Int] =
    Try {
      println("\nso far, so good")
      println("still there")
      // val i = 1/0
      // throw RuntimeException("We can't continue")
      42
    }

  def parseDate(str: String): Try[LocalDate] =
    Try(LocalDate.parse(str))

  def tryPeriod(str1: String, str2: String): Try[Period] =
    parseDate(str1).flatMap { (date1: LocalDate) =>
      parseDate(str2).map { (date2: LocalDate) =>
        Period.between(date1, date2)
      }
    }

  def tryPeriodAlt(str1: String, str2: String): Try[Period] =
    for
      date1 <- parseDate(str1)
      date2 <- parseDate(str2)
    yield
      Period.between(date1, date2)


object w6_01_tryType2:
  def parseDate(str: String): Try[LocalDate] =
    Try(LocalDate.parse(str))

  def readDateStrings(fileName: String): Try[Seq[String]] = 
    // Try {
    //   val source = Source.fromFile(fileName)
    //   val dateStrings = source.getLines().toSeq
    //   source.close()
    // }

    // need to gaurantee that source.close() is called, even when exception
    Using(Source.fromFile(fileName)) {
      source => source.getLines.toSeq
    }

  def parseDates(fileName: String): Try[Seq[LocalDate]] =
    readDateStrings(fileName).flatMap { (dateStrings: Seq[String]) => 
      dateStrings.foldLeft[Try[Seq[LocalDate]]](Success(Vector.empty)) {
        (tryDates, dateString) => 
          for
            dates <- tryDates
            date <- parseDate(dateString)
          yield
            dates :+ date
      }  
    }

type Errors = Seq[String]
type Validated[A] = Either[Errors, A]
object w6_01_tryTypeValidation:
    def validatedBoth[A, B](
      validatedA: Validated[A], 
      validatedB: Validated[B]
    ): Validated[(A, B)] =
      (validatedA, validatedB) match
        case (Right(a), Right(b)) => Right((a, b))
        case (Left(e), Right(_)) => Left(e)
        case (Right(_), Left(e)) => Left(e)
        case (Left(e1), Left(e2)) => Left(e1 ++ e2)

    def validateEach[A, B](as: Seq[A])(validate: A => Validated[B]): Validated[Seq[B]] =
      as.foldLeft[Validated[Seq[B]]](Right(Vector.empty)) {
        (validatedBs, a) =>
          val validatedB: Validated[B] = validate(a)
          validatedBoth(validatedBs, validatedB)
            .map((bs, b) => bs :+ b)
      }

    def parseDate(str: String): Validated[LocalDate] =
      Try(LocalDate.parse(str))
        .toEither
        .left.map(error => Seq(error.getMessage()))  

    def parseDates(strs: Seq[String]): Validated[Seq[LocalDate]] =
      validateEach(strs)(parseDate)
    // def parseDates(strs: Seq[String]): Validated[Seq[LocalDate]] =
    //   strs.foldLeft[Validated[Seq[LocalDate]]](Right(Vector.empty)) {
    //     (validatedDates, str) =>
    //       val validatedDate: Validated[LocalDate] = parseDate(str)
    //       validatedBoth(validatedDates, validatedDate)
    //         .map((dates, date) => dates :+ date)
    //   }


object w6_01_tryAndEither:
  type Errors = Seq[String]
  type Validated[A] = Either[Errors, A]

  def readDateStrings(fileName: String): Try[Seq[String]] =
    Using(Source.fromFile(fileName)) {
      source => source.getLines().toSeq
    }

  def parseDate(str: String): Validated[LocalDate] =
    Try(LocalDate.parse(str)) match
      case Failure(exception) => Left(Seq(exception.toString()))
      case Success(date)      => Right(date)

  def validatedBoth[A, B](validatedA: Validated[A], validatedB: Validated[B]): Validated[(A, B)] =
    (validatedA, validatedB) match
      case (Right(a), Right(b)) => Right((a, b))
      case (Left(e), Right(_)) => Left(e)
      case (Right(_), Left(e)) => Left(e)
      case (Left(e1), Left(e2)) => Left(e1 ++ e2)

  def validateEach[A, B](as: Seq[A])(validate: A => Validated[B]): Validated[Seq[B]] =
    as.foldLeft[Validated[Seq[B]]](Right(Vector.empty)) {
      (validatedBs, a) =>
        val validatedB: Validated[B] = validate(a)
        validatedBoth(validatedBs, validatedB)
          .map((bs, b) => bs :+ b)
    }

  def readAndParseDates(fileName: String): Try[Validated[Seq[LocalDate]]] = 
    readDateStrings(fileName).map {
      dateStrings => validateEach(dateStrings)(parseDate)
    }

  def runParseDates(fileName: String): Unit =
    readAndParseDates(fileName) match
      case Failure(exception) =>
        System.err.println(s"Unable to parse dates file: $exception")
      case Success(validDates) => 
        validDates match
          case Left(errors) => println(s"Invalid data: ${errors.mkString(", ")}")
          case Right(dates) => println(s"Parsed dates: ${dates.mkString(", ")}")
       

@main def main(): Unit =
  w6_01_tryCatch.tryCatchExample()

  
  val result = w6_01_tryType.tryTypeExampleFail()
    .recover {
      case ex: RuntimeException =>
        System.err.println(s"Something went wrong: $ex")
        println("Stopping the program")
    }

  val result2 = w6_01_tryType.tryTypeExampleFail()
  result2 match
    case Success(value) => println(s"result: $value")
    case Failure(throwable) => println(s"failed getting result: $throwable")

  val result3 = w6_01_tryType.tryTypeExampleSuccess()
  result3 match
    case Success(value) => println(s"result: $value")
    case Failure(_) => println("failed getting result")

  print("")
  println(s"period 1: ${w6_01_tryType.tryPeriod("2022-11-19", "2022-12-25")}")
  println(s"period 2: ${w6_01_tryType.tryPeriod("2022-19-19", "2022-12-25")}")
  println(s"period 3: ${w6_01_tryType.tryPeriod("2022-11-19", "2022-22-25")}")
  println(s"period 4: ${w6_01_tryType.tryPeriod("2022-19-19", "2022-22-25")}")

  print("")
  println(s"period 1: ${w6_01_tryType.tryPeriodAlt("2022-11-19", "2022-12-25")}")
  println(s"period 2: ${w6_01_tryType.tryPeriodAlt("2022-19-19", "2022-12-25")}")
  println(s"period 3: ${w6_01_tryType.tryPeriodAlt("2022-11-19", "2022-22-25")}")
  println(s"period 4: ${w6_01_tryType.tryPeriodAlt("2022-19-19", "2022-22-25")}")

  println(s"\nparseDate: ${w6_01_tryType2.parseDates("./src/main/scala/exceptions_w6/dates.bad.txt")}")
  println(s"\nparseDate: ${w6_01_tryType2.parseDates("./src/main/scala/exceptions_w6/dates.good.txt")}")

  println(s"\nparseDates: ${w6_01_tryTypeValidation.parseDates(List("2020-01-04", "2020-08-09"))}")
  println(s"parseDates: ${w6_01_tryTypeValidation.parseDates(List("not a date", "2020-08-09", "not a date 2"))}")

  println("\n running w6_01_tryAndEither.runParseDates with bad data")
  w6_01_tryAndEither.runParseDates("./src/main/scala/exceptions_w6/dates.bad.txt")
  println("\n running w6_01_tryAndEither.runParseDates with good data")
  w6_01_tryAndEither.runParseDates("./src/main/scala/exceptions_w6/dates.good.txt")
