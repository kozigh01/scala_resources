package lectures.`01_basics`

object `01_06_DefaultArgs` extends App {
  def tailRecursiveFactorial(n: Int, accumulator: Int = 1): Int =
    n match
      case x if x <= 1 => accumulator
      case x => tailRecursiveFactorial(n-1, n * accumulator)
  
  val fact10 = tailRecursiveFactorial(10, 1)
  println(fact10)
    
  println(tailRecursiveFactorial(10))


  // default parameters should be at the end of the parameter list
  //    when calling, can't omit leading args before first omitted argument (no required required after omitted)
  //    or use named arguments
  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving...")
  // savePicture(1920) // compiler is confused - thinks 1920 is the format
  savePicture(width = 800)  // use named arguments to clarify call - compiler is happyj
  savePicture(height = 100, width = 300, format = "bmp")  // named parameters can be passed in using different order

}
