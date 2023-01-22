package lectures.`02_OOP`

import java.nio.BufferOverflowException
import java.nio.BufferUnderflowException

object Exeptions extends App {
  println("---------- Exceptions ---------\n")

  val x: String = null
  // println(x.length) // crashes app with NullPointerException

  // 1. throwing exceptions
  // val aWeirdVlaue = throw new NullPointerException // aWeirdValue is type Nothing
  // val aWeirdVlaue2: String = throw new NullPointerException // Nothing can be assigned to any other type

  // throwable classes extend the Throwable class.
  //    Exceptions and Error are the major throwable subtypes
  //    Exceptions - something went wrong with app
  //    Error - something went wrong with the system - such as, stack overflow error (jvm went wrong)

  // // 2. catching Exceptions
  // def getInt(withExceptions: Boolean): Int =
  //   if withExceptions then throw new RuntimeException("No int for you")
  //   else 42

  // // potentialFail is type AnyVal: from unifying both cases Int and RuntimeException, closest ancestor is AnyVal
  // try
  //   getInt(true)
  // catch
  //   case e: RuntimeException => println(s"caught a runtime exception: $e")
  // finally
  //   // code that will execute NO MATTER WHAT
  //   println("executing finally")

  // val potentialFail = try
  //   getInt(false)
  // catch
  //   case e: RuntimeException => println(s"caught a runtime exception: $e")
  // finally
  //   // code that will execute NO MATTER WHAT
  //   //    finally is optional
  //   //    finally does no influence the return type of the try/catch expression
  //   //    use finally only for side effects
  //   println("executing finally for successful getInt call")
  // println(potentialFail)


  // try
  //   val aWeirdVlaue = throw new NullPointerException
  // catch
  //   // Exceptions not in a case are not caught
  //   case e: RuntimeException => println(s"caught a runtime exception: $e")
  // finally
  //   println("executing finally")


  // // 3. defining custom exceptions - typically, only create custom Exceptions and not custom Errors
  // class MyException(message: String = null) extends Exception(message)
  // val exception = MyException("custom exception")
  // try
  //   throw exception
  // catch
  //   case e: MyException => println(s"caught a MyException: $e")


  //  Exercises

  // crash app with OutOfMemoryError
  // val bigArray: Array[Int] = Array.ofDim(Int.MaxValue)

  // crash app with StackOverflowError
  // val infiniteRec: () => Void = () => infiniteRec()
  // infiniteRec()

  // define little calculator
  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculatioExeption extends Exception("Division by zero")

  object Calculator:
    def add(x: Int, y: Int): Int =
      (x, y) match
        case (x: Int, y: Int) => 
          val result = x + y

          if (x > 0 && y > 0 && result < 0) then
            throw new OverflowException
          else if (x < 0 && y < 0 && result > 0) then
            throw new UnderflowException

          result

    def div(x: Int, y: Int): Int =
      if (y == 0) then throw MathCalculatioExeption()      
      x / y

    // sub, mult and div work similarly: see Udemy video for details
      
  end Calculator

  // val overflowAdd = Calculator.add(Int.MaxValue, 10)
  // val underflow = Calculator.add(Int.MinValue, -10)
  // val divByZero = Calculator.div(10, 0)
  println(s"Calculator.add(10, 5): ${Calculator.add(10, 5)}")
  
}
