package lectures.`04_PatternMatching`

object Scala3BracelessSyntax:
  def part1 =    
    println("----- Part 1 -----\n")

    // if expressions
    //    scala 3
    val if1a = if 2 > 3 then "bigger" else "smaller"
    val if1b = 
      if 2 > 3 then "bigger" 
      else "smaller"
    val if1c = 
      if 2 > 3 then 
        "bigger" 
      else 
        "smaller"
    //  scala 2
    val if1d = if (2 < 3) "bigger" else "smaller"
    val if1e = 
      if (2 < 3) "bigger" 
      else "smaller"


    // for comprehensions
    //   scala 2
    val for1a = for {
      n <- List(1,2,3)
      s <- List("black", "white")
    } yield s"$n$s"
    println(s"for1a: $for1a")
    //    scala 3
    val for1b = for
      n <- List(1,2,3)
      s <- List("b", "w")
    yield s"$n$s"
    println(s"for1b: $for1b")

  // pattern matching
  val n = 42
  //    scala 2
  val pm1a = n match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "unknown"
  }
  //    scala 3
  val pm1b = n match
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "unknown"

  //  methods
  //    scala 3
  def fun1(a: Int): Int =
    val partialResult = 40
    partialResult + 2

  def fun2(a: Int): Int =
    val partialResult = 40
    partialResult + 2
  end fun2

end Scala3BracelessSyntax


@main def Scala3BracelessSyntaxMain =
  println("---------- Scala 3 Braceless Syntax ----------")
  
  Scala3BracelessSyntax.part1
  
  println("\n---------- Scala 3 Braceless Syntax ----------")
end Scala3BracelessSyntaxMain