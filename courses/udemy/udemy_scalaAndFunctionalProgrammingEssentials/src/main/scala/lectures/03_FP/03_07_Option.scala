package lectures.`03_FP`

import scala.util.Random

object Options:
  def optionExamples = 
    val map1 = Map("Bob" -> 42)
    val mapFound = map1.get("Bob")
    val mapNotFound = map1.get("Tony")
    println(s"mapFound, mapNotFound: $mapFound, $mapNotFound")

    val list1 = List(1,2,3)
    val list1HeadOption = list1.headOption
    val listFound = list1.find(_ % 2 == 0)
    val listNotFound = list1.find(_ % 5 == 0)
    println(s"list1HeadOption, listFound, listNotFound: $list1HeadOption, $listFound, $listNotFound")

  def optionsBasics =
    val option1 = Option(4)
    val option2 = Option.empty
    val option2_1 = Option(null)
    println(s"option1, option2, option3: $option1, $option2, $option2_1")

    val option3 = Some(42)
    val option4 = None
    println(s"option3, option4: $option3, $option4")

    // unsafe api
    def unsafeMethod(): String = null
    val result1 = Some(unsafeMethod())   // this is BAD!! never use Some(null)
    val result2 = Option(unsafeMethod())   // do this instead
    println(s"result1, result2: $result1, $result2")

    //  chained methods
    def backupMethod(): String = "A valid result"
    val chainedResult1 = Option(unsafeMethod())
    val chainedResult2 = chainedResult1.orElse(Option(backupMethod()))
    println(s"chainedResult1, chainedResult2: $chainedResult1, $chainedResult2")
    println(s"Option(42).orElse(43): ${Option(42).orElse(Option(43))}")
    println(s"Option(null).orElse(43): ${Option(null).orElse(Option(43))}")

    //  DESIGN unsafe APIs -- return Options
    def betterUnsafeMethod(): Option[String] = Option(null)
    def betterBackupMethod(): Option[String] = Option("A valid result")
    val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()
    println(s"betterChainedResult: $betterChainedResult")

  def optionsBasics2 =
    // functions on options
    println(s"Option(42).isEmpty: ${Option(42).isEmpty}")
    println(s"Option(null).isEmpty: ${Option(null).isEmpty}")

    // val x = Option(null).get  // UNSAFE - DO NOT USE THIS -- throws exception on empty Option

    // map, flatMap, filter
    val option1 = Option(4)
    println(s"Option(4).map(_ * 2): ${Option(4).map(_ * 2)}")
    println(s"Option[String](null).map(x => x * 2): ${Option[String](null).map(x => x * 2)}")
    println(s"Option(\"Hello\").map(x => x * 2): ${Option("Hello").map(x => x * 2)}")
    println(s"Option[String](null).flatMap(x => Option(x * 2)): ${Option[String](null).flatMap(x => Option(x * 2))}")
    println(s"Option(\"Scala\").flatMap(x => Option(x * 2)): ${Option("Scala").flatMap(x => Option(x * 2))}")
    println(s"Option(5).filter(_ > 5): ${Option(5).filter(_ > 5)}")
    println(s"Option(5).filter(_ >= 5): ${Option(5).filter(_ >= 5)}")

  // for comprehensions (since we have proper map, flatmap and filter)
  def exerercises() =
    val config: Map[String, String] = Map(
      // fetched from elsewhere -- may contain missing data
      "host" -> "176.45.36.1",
      // "host" -> null,
      // "port" -> "80"
      "port" -> null
    )
    val result: Option[String] = for
      host <- config.get("host") if host != null
      port <- config.get("port") if port != null
      // connection1 <- Connection(host, port)
    yield {
      val connection1: Option[Connection] = Connection(host, port)
      connection1 match
        case None => "connection failed"
        case Some(conn) => conn.connect
    }
    result match
      case None => println("there is a configuration issue")
      case Some(msg) => println(msg)

    // alternate approach
    val host1 = config.get("host")
    val port1 = config.get("port")
    val conn1 = host1.flatMap(h => port1.flatMap(p => Connection(h, p)))
    val connStatus1 = conn1.map(conn => conn.connect)
    connStatus1.foreach(println)
    println(connStatus1)
    
    
  class Connection:
    def connect = "Connected"
    
  object Connection:
    val random = Random(System.nanoTime)
    def apply(host: String, port: String): Option[Connection] =
      random.nextBoolean() match
        case true => Option(new Connection)
        case false => Option(null)
  
@main def optionsMain =
  // Options.optionExamples
  // Options.optionsBasics
  // Options.optionsBasics2
  Options.exerercises() 