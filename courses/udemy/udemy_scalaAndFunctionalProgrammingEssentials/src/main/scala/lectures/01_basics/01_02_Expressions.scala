package lectures.`02_basics`

object Expressions {
  def main(args: Array[String]): Unit =
    val x = 1 + 2  // expression is (1 + 2)
    println(x)

    println(1 == x)  // == != > >= < <=

    println(!(1 == x)) // ! && ||

    var aVariable = 2
    aVariable += 3  // += -+ *= /=  --> these are all side effects
    println(aVariable)

    // Instruction (something you do) vs Expressions (something that has a value and type)
    //    In Scala -- everything is an expression: for example and if expression
    val aCondition = true
    val aConditionValue = if aCondition then 15 else 31  // an if expression
    println(aConditionValue)
    println(if (aCondition) 15 else 31)   // using if expression in scala 2 syntax

    //  Loops are discouraged in scala - they are more imerative than functional in nature
    //  so, avoid using loops

    // everything in scala is an expression: for example:
    val aWeirdValue = (aVariable = 3)   // an expression that returns the type Unit: only possible value of Unit is ()
    println(aWeirdValue)

    // side effects are expressions that return Unit
    //    ex: println(), while, reassigning of var, ...
    var aPrintln = println("side effect")
    println(aPrintln)

    // Code blocks (are expressions also) - last expression of code block will be the value of the code block
    val aCodeBlock = {
      val y = 2
      val z = y + 1

      if z > 2 then "hello" else "goodbye"
    }
    println(aCodeBlock)
}
