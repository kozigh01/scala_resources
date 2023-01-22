package lectures.`03_FP`

object `03_01_WhatsAFunction` extends App {
  // use functions as first class elements
  //    problem: come from an object oriented world (jvm)
  //  scala uses objects and the apply feature to make classes look like functions
  //  ALL SCALA FUNCTIONS ARE OBJECTS (INSTANCES OF SCALA CLASSES)
  //  scala includes syntactic sugar for working with FunctionX traits

  trait MyFunction[A, B]:
    def apply(element: A): B

  val doubler = new MyFunction[Int, Int]:
    override def apply(element: Int): Int = element * 2

  // can use double like a function, curtesy of the apply() functionality
  val double2 = doubler(2)  // a class instantiation that acts like a function
  println(s"double2: $double2")

  // scala includes classes for functions: Function1~Function22
  val doubler2 = new Function1[Int, Int]:
    override def apply(v1: Int): Int = v1 * 2
  val double3 = doubler2(3)
  println(s"double3: $double3")

  val concator2 = new Function2[String, String, String]:
    override def apply(v1: String, v2: String): String = v1 + v2
  val concat2 = concator2("Hello, ", "World")
  println(s"concat2: $concat2")


  // Exercises

  // 1. function which takes 2 strings and concatenates them
  val myConcat: Function2[String, String, String] = new Function2[String, String, String]:
    override def apply(v1: String, v2: String): String = v1 + v2
  val myConcat2: (String, String) => String = new Function2[String, String, String]:
    override def apply(v1: String, v2: String): String = v1 + v2
  println(myConcat("this ", "concatenates"))
  println(myConcat2("this ", "concatenates, using myConcat2"))

  // 2. from course level - convert MyPredicate and MyTransformer into Function types
  trait MyPredicate[T, Boolean] extends Function1[T, Boolean]
  trait MyTransformer[A, B] extends Function1[A, B]

  val myPredicate = new MyPredicate[Int, Boolean]:
    override def apply(v1: Int): Boolean = (v1 < 10)
  println(s"myPredicate(8): ${myPredicate(8)}")
  println(s"myPredicate(11): ${myPredicate(11)}")

  val myTransformer = new MyTransformer[Int, String]:
    override def apply(v1: Int): String = "hello " * v1
  println(s"myTransformer(3): ${myTransformer(3)}")

  // 3. function that takes an int and returns a function that takes an int and returns an int
  val myFunc: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]]:
    override def apply(v1: Int): Int => Int = new Function1[Int, Int]:
      override def apply(v2: Int): Int = v1 + v2

  val adder2: Function1[Int, Int] = myFunc(2)
  println(s"myFunc(2)(3): ${myFunc(2)(3)}") // example of a "curried function"
  println(s"adder2(3): ${adder2(3)}")
}


