package lectures.`03_FP`

object HOFandCurriedFunctions extends App {
  
  // val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???

  // Higher order function (HOF): takes a function as param or returns a function
  //    Example: map, flatMap, filter

  // function that applies a function n times over a value x
  //    nTimes(f, n, x)
  //    nTimes(f, 3, x) = f(f(f(x)))
  val nTimes: (Int => Int, Int, Int) => Int = (f, n, x) =>
    n match
      case n if n <= 0 => x
      case n => nTimes(f, n-1, f(x))
  println(s"nTimes(_ * 2, 3, 10): ${nTimes(_ * 2, 3, 10)}")
  println(s"nTimes(_ + 1, 7, 10): ${nTimes(_ + 1, 7, 10)}")
    
  // nTimesBetter
  val nTimesBetter: (f: Int => Int, n: Int) => (Int => Int) = (f,n) =>
    n match
      case n if n <= 0 => (x: Int) => x
      case n => (x: Int) => nTimesBetter(f, n-1)(f(x))
  println(s"nTimesBetter(_ * 2, 3)(10): ${nTimesBetter(_ * 2, 3)(10)}")
  val increment10By5 = nTimesBetter(_ + 10, 5)
  println(s"increment10(10): ${increment10By5(5)}")
      
  // curried functions
  val superAdder: Int => Int => Int = x => y => x + y
  println(s"superAdder(5)(6): ${superAdder(5)(6)}")
  val adder5: Int => Int = superAdder(5)  // y => 3 + y
  println(s"adder5(6): ${adder5(6)}")

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: Double => String = curriedFormatter("%10.8f")
  println(s"%4.2f".format(Math.PI))
  println(s"standardFormat(Math.PI): ${standardFormat(Math.PI)}")
  println(s"preciseFormat(Math.PI): ${preciseFormat(Math.PI)}")


  // Exercises
  /*
    1. Expand My List
        - foreach method A => Unit
        - sort function ((A, A) => Int) => MyList
        - zipWith (list, (A, A) => MyList[B])
        - fold(start)(function) => a value

    2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
  */
  val toCurry: ((Int, Int) => Int) => (Int => Int => Int) = (f: ((Int, Int) => Int)) =>
    (a: Int) => ((b: Int) => f(a, b))
  val curried1 = toCurry((x: Int, y: Int) => x + y)
  val add6 = curried1(6)
  println(s"add6(7): ${add6(7)}") 

  val compose: (Int => Int, Int => Int) => Int => Int = (f: (Int => Int), g: (Int => Int)) =>
    (x: Int) => f(g(x))
  println(s"compose(x => x + 1, y => y * 2)(3): ${compose(_ + 1, _ * 2)(3)}")

  val andThen: (Int => Int, Int => Int) => Int => Int = (f: (Int => Int), g: (Int => Int)) =>
    (x: Int) => g(f(x))
  println(s"andThen(x => x + 1, y => y * 2)(3): ${andThen(_ + 1, _ * 2)(3)}")
}
