package lectures.`03_FP`

object MapFlatmapFilterForComprehensions:
  def listExamples() =
    val list1 = List(1,2,3)
    println(s"list.head: ${list1.head}")
    println(s"list1.tail: ${list1.tail}")

    println(s"list1.map(_ + 1): ${list1.map(_ + 1)}")
    println(s"list1.map(_ + \" is a number\"): ${list1.map(_ + " is a number")}")

    println(s"list1.filter(_ % 2 == 0): ${list1.filter(_ % 2 == 0)}")

    val toPair: Int => List[Int] = x => List(x, x*2)
    println(s"list1.flatMap(toPair): ${list1.flatMap(toPair)}")
    println(s"list1.flatMap(x => List(x, x * 5)): ${list1.flatMap(x => List(x, x * 5))}")

    list1.foreach(println)

    // Exercise
    val numbers = List(1,2,3,4)
    val chars = List('a', 'b', 'c', 'd')
    val colors = List("black", "white")
    val combos1 = chars.zip(numbers).map((pair: (Char, Int)) => s"${pair._1}${pair._2}")
    println(s"combos1: $combos1")
    val combos3 = colors.flatMap(col => chars.flatMap(char => numbers.map(n => s"$col-$char$n")))
    println(s"combos3: $combos3")
    
    // this is exactly equivalent to the flatMap for combos3: compiler does this under the hood
    val combos2 = 
      for
        col <- colors
        c <- chars
        n <- numbers
      yield s"$col-$c$n"
    println(s"combos2: $combos2")

    val combos4 =   // for comprehension with gaurds
      for
        col <- colors
        c <- chars
        n <- numbers if n % 2 == 0  // translates into numbers.filter(_ % 2 == 0).flatMap(....)
      yield s"$col-$c$n"
    println(s"combos4: $combos4")

    // can do side-effects with for comprehensions
    for
      num <- numbers
    do println(num)

    // syntax overload
    list1.map { x =>
      x + 2
    }

  def exercises1(): Unit =
    // 1. MyList supports for comprehensions ?    
    import exercises.`02_01`.{ MyList_02_01 }
    val myList1 = MyList_02_01.MyListEmpty().add(1).add(2).add(3)
    val for1 =
      for
        n <- myList1
      do println(s"myList1 element: $n")

    // 2. implement a small collection with at most ONE element: Maybe[+T]
    //    - map, flatMap, filter
    abstract class Maybe[+A]:
      def map[C](func: A => C): Maybe[C]
      def flatMap[C](func: A => Maybe[C]): Maybe[C]
      def filter(func: A => Boolean): Maybe[A]

    case object MaybeNot extends Maybe[Nothing]:
      def map[B](func: Nothing => B): Maybe[Nothing] = MaybeNot
      def flatMap[B](func: Nothing => Maybe[B]): Maybe[Nothing] = MaybeNot
      def filter(func: Nothing => Boolean): Maybe[Nothing] = MaybeNot

    case class Just[+A](value: A) extends Maybe[A]:
      def map[C](f: A => C): Maybe[C] = Just(f(value))
      def flatMap[C](f: A => Maybe[C]): Maybe[C] = f(value)
      def filter(f: A => Boolean): Maybe[A] = 
        f(value) match
          case true => this
          case false => MaybeNot

    
    val maybe1: Maybe[Int] = MaybeNot
    println(s"maybe1: $maybe1")
    println(s"maybe1.map(x => 4): ${maybe1.map(x => 4)}")
    println(s"maybe1.flatMap(x => Just(5)): ${maybe1.flatMap(x => Just(5))}")
    println(s"maybe1.filter(x => x % 2 == 0): ${maybe1.filter(x => x % 2 == 0)}")

    val maybe2: Maybe[Int] = Just(42)
    println(s"maybe2: $maybe2")
    println(s"maybe2.map(x => 4): ${maybe2.map(x => 4)}")
    println(s"maybe2.flatMap(x => Just(5)): ${maybe2.flatMap(x => Just(5))}")
    println(s"maybe2.filter(x => x % 2 == 0): ${maybe2.filter(x => x % 2 == 0)}")
    println(s"maybe2.filter(x => x % 2 != 0): ${maybe2.filter(x => x % 2 != 0)}")

end MapFlatmapFilterForComprehensions


@main def mapFlatmapFilterForComprehensionsMain(): Unit =
  // MapFlatmapFilterForComprehensions.listExamples()
  MapFlatmapFilterForComprehensions.exercises1()

