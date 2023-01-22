package lectures.`04_PatternMatching`

object PatternsEverywhere extends App:
  // big idea #1: catches are actually matches
  try
    val x = 33
  catch
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "null pointer"
    case _ => "something else"

  // big idea #2: generators are also based on pattern matching
  val list1 = List(1,2,3,4)
  val list1a = for
    x <- list1 if x % 2 == 0
  yield 10 * x
  println(s"list1a: $list1a")

  // for comprehensions use destructuring like matchers - similar to PATTERN MATCHING
  val list2 = List((1,2), (3,4))
  val list2a = for
    (first, second) <- list2
  yield first * second
  println(s"list2a: $list2a")

  // big idea #3: variable definitons destructuring can use PATTERN MATCHING
  val tuple1 = (1,2,3)
  val (a, b, c) = tuple1
  println(s"tuple destructuring: $a, $b, $c")

  val h1 :: t1 = List(1,2,3,4)
  println(s"list destructuring: $h1, $t1")

  val l1 @ List(1, 2, sl @ _*) = List(1,2,3,4)
  println(s"list destructuring: ${l1.length}, ${sl.length}")

  case class Person(name: String, age: Int)
  val Person(n1, a1) = Person("Dude", 21)
  println(s"Person destructuring: $n1, $a1")

  // big idea #4: partial function are based on MATTERN MATCHING
  val list3 = List(1,2,3,4).map {
    case v if v % 2 == 0 => s"is even: v = $v"
    case 1 => "the one"
    case _ => "something else"
  }   // partial function listeral
  println(s"list3: $list3")

  //    equivalent to:
  val list4 = List(1,2,3,4).map(x => 
    x match
      case v if v % 2 == 0 => s"is even: v = $v"
      case 1 => "the one"
      case _ => "something else"
  )
  println(s"list4: $list4")

  
