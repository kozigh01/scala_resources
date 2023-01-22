package lectures.`04_PatternMatching`

object AllThePatterns:
  def part1 =    
    println("----- Pattern Matching - All The Patterns -----\n")

    // 1. constants
    val x: Any = "Scala"
    val contants = x match
      case 1 => "a number"
      case "Scala" => "THE Scala"
      case true => "The Truth"
      case AllThePatterns => "A singleton object"
      case _ => "I don't know"
    println(s"constants: $contants")

    // 2. match anything
    val matchAnything = x match
      case _ => "I'm matching anything"
    println(s"matchAnything: $matchAnything")

    // 2.2 match a variable
    val matchAnything2 = x match
      case something => s"I'm matching anything: $something"
    println(s"matchAnything2: $matchAnything2")   

    // 3. tuples
    val tuple1 = 1 -> 2
    val match1 = tuple1 match
      case (1, 1) => "tuple (1, 1)"
      case (something, 2) => s"tuple (something, 2) - something = $something"
    println(s"match1: $match1")

    // 3a. nested tuples
    val tuple2 = (1, (2, 3))
    val match2 = tuple2 match
      case (_, (2, v)) => s"I found v = $v"

    // 4. case classes - costructor pattern (can be nested)
    // import exercises.`02_01`.MyList_02_01.{MyList, MyListEmpty, MyListNonEmpty}
    // val list1 = MyListNonEmpty(1, MyListNonEmpty(2, MyListEmpty()))
    // val match3 = list1 match
    //   case MyListEmpty => "I'm empty"
    //   case MyListNonEmpty(h, t) => s"Not empty: head = $h, tail = $t"
    //   case MyListNonEmpty(h, t(subhead, subtail)) => s"Not empty"
    // println(s"match3: $match3")
    case class Person(name: String, age: Int)
    Person("Bob", 33) match
      case Person(n, a) => println(s"Person(n, a) matches: name = $n, age = $a")

    // 5. list patterns
    val list1 = List(1,2,3,42)
    val match3 = list1 match
      case List(1, _, _, _) => ()  // extractor - matches against a list of length 4
      case List(1, _*) => () // matcheds against a list of arbitrary length, that starts with 1
      case 2 :: List(_) => ()  // infix pattern
      case List(1,2,3) :+ 42 => ()  // another infix pattern

    List(1,2,3,42,4,5) match
      case 1 :: List(_) => println("1 :: List(_) matches")  // not found
      case 1 +: List(_*) :+ 42 => println("1 +: List(_*) :+ 42 matches") // not found
      case 1 +: List(_, _, 42, _*) => println("1 +: List(_, _, 42, _*) matches") // matches
      case 1 :: List(_*) => println("1 :: List(_*) matches") // matches
      case _ => println("List match: nothing found")

    // 6. type specifiers
    val unknown: Any = 2
    val match4 = unknown match
      case list: List[Int] => println("list: List[Int] - matches")   // explicit type specifier
      case str: String => println("str: String - matches")
      case int: Int => println("int: Int - matches")
      case _ => println("no matches")

    // 7. name binding (works with nested patterns)
    Person("Bob", 33) match
      case p @ Person(_, _) => println(s"matches person: name = ${p.name}, age = ${p.age}")
    List(1,2,3,4) match
      case l @ List(1, _*) => println(s"matches list: length = ${l.length}")
    List(1,2,3,List(4,5)) match
      case l @ List(1, 2, sl @ _*) => println(s"matches list: length = ${l.length}, sublist length: ${sl.length}")
     
    // 8. multi-patterns
    List(0,2,3,4) match
      case List(1, _*) | List(_, 2, _*) => println("multi-pattern matches")
    
    // 9. if guards
    List(1,2,3,4) match
      case List(1, 2, sl @ _*) if sl.length > 2 => println(s"if guard: matches with sl length > 2 (${sl.length})") 
      case List(1, 2, sl @ _*) if sl.length == 2 => println(s"if guard: matches with sl length == 2") 
    Person("Bob", 33) match
      case p @ Person(_, a) if a == 33 => println("Person matches when age == 33")

    // 10. TYpe Erasure: JVM removes generic parameter from matches (due to JVM version backwrads compatibiity)
    List(1,2,3,4) match
      case l: List[String] => println("matches List[String]")  // THIS MATCHES!!!
      case l: List[Int] => println("matches List[Int]")
      case _ => "no matches"
    

@main def AllThePatternsMain =
  println("---------- All THe Patterns ----------")
  
  AllThePatterns.part1
  
  println("\n---------- All THe Patterns ----------")