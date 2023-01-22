package lectures.`01_basics`

val str: String = "Hello, I am learning Scala"

@main def StingOps(): Unit =
  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.startsWith("hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.toUpperCase())
  println(str.toUpperCase)
  println(str.length)

  // Scala specific string functions/operators
  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println(aNumber)
  // println("45a".toInt)  // throws NumberFormatException
  println('a' +: aNumberString :+ 'z')
  println(str.reverse)
  println(str.take(2))

  // Scala specific string interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and my age is $age"
  println(greeting)
  val greeting2 = s"Hello, my name is $name and I will be turning ${age + 1}"
  println(greeting2)

  // F-interpolators (using printf formatting formats)
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)

  // raw interpolator
  println(raw"This is a \n newline")
  val escaped = "This is a \n newline"
  println(raw"$escaped")  // injected variables still get interpolated