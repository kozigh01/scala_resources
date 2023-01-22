package concurrent_w6_02

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future
import scala.util.Random
import scala.util.control.NonFatal

object w6_02_concurrency {
  def getPagesCount(): Future[Int] =
    Future(42)

  def getPage(page: Int): Future[String] =
    if Random.nextDouble() > 0.95 then
      Future.failed(Exception(s"Timeout when fetching page $page"))
    else
      Future(s"Page $page")

  def getAllPages(): Future[Seq[String]] =
    getPagesCount().flatMap { pagesCount =>
      val allPages = 1 to pagesCount
      Future.traverse(allPages)(getPage)  
    }

  def getAllPagesSequentially(): Future[Seq[String]] =
    getPagesCount().flatMap { pagesCount => 
      val allPages = 1 to pagesCount
      allPages.foldLeft[Future[Seq[String]]](Future.successful(Vector.empty)) {
        (eventualPreviousPages, pageNumber) =>
          eventualPreviousPages.flatMap { previousPages => 
            getPage(pageNumber).map(pageContent => previousPages :+ pageContent)
          }
      }
    }

  def runGetAllPages(): Unit =
    getAllPages()
      .map(_ => println("It was a success"))
      .recover(throwable => println(s"somethig went wrong: $throwable"))
      .onComplete(println)

  def runGetAllPagesSequentially(): Unit =
    getAllPages()
      .map(_ => println("It was a success"))
      .recover(throwable => println(s"somethig went wrong: $throwable"))
      .onComplete(println)

  def resilientGetPage(page: Int): Future[String] =
    val maxAttemps = 3

    def attempt(remainingAttempts: Int): Future[String] = 
      if remainingAttempts == 0 then
        Future.failed(Exception(s"Failed after $maxAttemps attemps"))
      else
        println(s"Tryng to fetch page $page ($remainingAttempts remaining attemps)")
        getPage(page).recoverWith {
          case NonFatal(_) => 
            System.err.println(s"Fetching page $page failed...")
            attempt(remainingAttempts - 1)
        }

    attempt(maxAttemps)
}

@main def main(): Unit =
  println("started")
  // w6_02_concurrency.runGetAllPages()
  // w6_02_concurrency.runGetAllPagesSequentially()
  w6_02_concurrency.getAllPages().onComplete(println)
  println("ended")
