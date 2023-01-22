package lectures.`03_basics`

@main def main(): Unit =
  def aFunction(a: String, b: Int): String =
    a + " " + b

  println(aFunction("hello", 3))

  //  a function with no parameters can be called with just the function name
  def aParameterlessFunction: Int = 42
  println(aParameterlessFunction)

  // instead of using loops, use a recursive function: never use imperitive code (loops) in scala
  def aRepeatedFunction(aString: String, n: Int): String =
    n match
      case 1 => aString
      case x => aString + aRepeatedFunction(aString, n - 1)  

  println(aRepeatedFunction("ab", 5))

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)
  aFunctionWithSideEffects("Hello scala")

  // functions in code blocks
  def aBigFunction(n: Int): Int =
    def aSmallerFunction(a: Int, b: Int) = a + b * 2
    aSmallerFunction(n, n-1)

  println(aBigFunction(5))

  //  Exercises
  def exercise01(name: String, age: Int): String = s"Hi, my name is $name and I am $age years old"
  println(exercise01("Bob", 8))

  // factorial
  def exercise02(n: Int): Int =
    n match
      case 1 => 1
      case x => x * exercise02(x - 1)
  println(exercise02(5))

  // fibonacci
  def exercise03(n: Int): Int =
    n match
      case 1 => 1
      case 2 => 1
      case x => exercise03(x-1) + exercise03(x-2)
  println(exercise03(8))

  // is prime
  def exercise04(n: Int): Boolean =
    def isPrimeUntil(t: Int): Boolean =
      t match
        case n if n <= 1 => true
        case n if n > 1 => n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)

  println(exercise04(37))    
  println(exercise04(629))  // 37 * 17   

    
      
    
    