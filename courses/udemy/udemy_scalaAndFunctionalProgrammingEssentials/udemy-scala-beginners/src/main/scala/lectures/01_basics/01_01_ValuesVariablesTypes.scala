package lectures.`01_basics`

object ValuesVariablesTypes extends App {
  val x: Int = 42
  println(s"the value x = $x")

  // Vals are immutable
  // x = 2 // this throws an exception

  val y = 43 // compiler can infer type of val
  println(s"the value y = $y")

  // Basic types
  val aString: String = "hello"
  val aBoolean: Boolean = false
  val aChar: Char = 'b'
  val aInt: Int = Int.MaxValue
  val aShort: Short = Short.MaxValue
  val aLong: Long = Long.MaxValue
  val aLong2: Long = 987243987098L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14159

  // Variables
  var aVariable: Int = 4
  aVariable = 6 // variables are not immutable - can do side effects
  aVariable += 3
  
}

