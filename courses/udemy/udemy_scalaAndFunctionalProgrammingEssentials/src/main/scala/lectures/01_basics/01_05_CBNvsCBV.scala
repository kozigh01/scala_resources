package lectures.`01_basics`

object `01_05_CBNvsCBV` {
  def calledByValue(x: Long): Unit =
    println(s"by value: $x")
    println(s"by value: $x")

  // => means call parameter by name
  //    evaluation of x is deferred until called in the function body
  //      and is evaluated each time it is used
  def calledByName(x: => Long): Unit =
    println(s"by name: $x")
    println(s"by name: $x")

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)  // y is call by name, because of the => in parameter definition

  @main def CallByNameVsCallByValue(): Unit =
    calledByValue(System.nanoTime())
    
    // 'System.nanoTime()' is passed in and evaluated each time it's referenced
    //    Equivalent to:
            // def calledByName(x: => Long): Unit =
            //   println(s"by name: ${System.nanoTime()}")
            //   println(s"by name: ${System.nanoTime()}")
    calledByName(System.nanoTime())  

    // printFirst(infinite(), 34)  // stack overflow error 
    printFirst(34, infinite())  // no stack overflow error, since infinte() is not evaluated in method

}
