package lectures.`01_basics`

import scala.annotation.tailrec

// @tailrec // this will cause an exception
def factorial(n: Int): Int =
  n match
    case 1 => 1
    case x => 
      println(s"Computing factorial of $n - first need factorial of ${n-1}")
      val result = n * factorial(n-1)
      println(s"Computed factorial of $n: $result")
      result
  
def factorial2(n: Int): BigInt =
  @tailrec
  def factorialHelper(x: Int, accumulator: BigInt): BigInt =
    x match
      case 1 => accumulator
      case y => factorialHelper(y-1, y * accumulator)

  factorialHelper(n, 1)
    

@main def main_01_05(): Unit =
  println("\n----------- Recursion ----------\n")

  println(factorial(10))
  // println(factorial(5000))   // throws StackOverflowError


  // factorial2 uses tail-recursion, the recursive call is the last expression evaluated
  //  key is that the recursive call is the last expression
  //  use @tailrec to have compiler issue exception when not tail recursive
  println(factorial2(10))
  println(factorial2(5000))

  //  Exercises
  def concat(str: String, n: Int): String =
    @tailrec
    def helper(n: Int, accumulator: String): String =
      n match
        case 1 => accumulator
        case x => helper(n-1, accumulator + str)

    helper(n, "")
  println(concat("hello", 4))

  def isPrime(n: Int): Boolean =
    @tailrec
    def helper(t: Int, isStillPrime: Boolean): Boolean =
      (t, isStillPrime) match
        case (_, false) => false
        case (x, true) if x <= 1 => true
        case (x, true) => helper(t-1, n%t != 0 && isStillPrime)
    helper(n / 2, true)
  println(isPrime(2003))
  println(isPrime(17 * 23))

  // @tailrec
  def fibonacci(n: Int): Int =
    @tailrec
    def helper(x: Int, last: Int, prevLast: Int): Int =
      x match
        case y if y >= n => last
        case y if y < n => helper(y+1, last + prevLast, last)

    if n <= 2 then 1 else helper(2, 1, 1)
  println(fibonacci(1))
  println(fibonacci(2))
  println(fibonacci(3))
  println(fibonacci(4))
  println(fibonacci(5))
  println(fibonacci(6))
  println(fibonacci(7))
  println(fibonacci(8))