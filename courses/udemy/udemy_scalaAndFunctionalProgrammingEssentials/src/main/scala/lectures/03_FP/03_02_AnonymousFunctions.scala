package lectures.`03_FP`

object AnonymousFunctions extends App {

  val doublerOld: Function1[Int, Int] = new Function1:
    override def apply(v1: Int): Int = v1 * 2

  // anonymous function (LAMBDA)
  val doubler: Int => Int = x => x * 2 // functonal programming equivalent to doublerOld
  val doublerAlt = (x: Int) => x * 2 // functonal programming equivalent to doublerOld
  println(s"doubler: ${doubler(4)}")

  // multiple parameters
  val adder: (a: Int, b: Int) => Int = (a, b) => a + b

  // no parameters
  val doSomething: () => 3 = () => 3
  println(s"doSomething: $doSomething")
  println(s"doSomething(): ${doSomething()}")
  
  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }
  println(s"stringToInt(\"55\"): ${stringToInt("55")}")

  // MOAR syntax
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  println(s"niceIncrementer(4): ${niceIncrementer(4)}")

  val niceAdder: (Int, Int) => Int = _ + _    // equivalent to (a,b) => a + b
  println(s"niceAdder(12, 13): ${niceAdder(12, 13)}")


  // Exercise
  // convert myFuncOld to an anonymous function
  val myFuncOld: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]]:
    override def apply(v1: Int): Int => Int = new Function1[Int, Int]:
      override def apply(v2: Int): Int = v1 + v2

  val myFunc: Int => Int => Int = x => y => x + y
  val myFuncAlt = (x: Int) => (y: Int) => x + y
  println(s"myFuncOld(4)(5): ${myFuncOld(4)(5)}")
  println(s"myFunc(4)(5): ${myFunc(4)(5)}")
  println(s"myFuncAlt(4)(5): ${myFuncAlt(4)(5)}")
}
