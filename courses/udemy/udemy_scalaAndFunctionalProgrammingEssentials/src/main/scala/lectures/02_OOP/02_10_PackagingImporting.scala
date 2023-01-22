package lectures.`02_OOP`

object PackagingImporting extends App {

  // package members are accessible by their simple name
  val writer = `02_01_OOPBasics`.Writer("Daniel", "RockTheJVM", 2019)

  // if not in the same package:
  //    1. use fully qualified name
  val factorial1 = lectures.`01_basics`.factorial(7)
  println(s"factorial1: $factorial1")
  
  //    2. import the package or specific name
  import lectures.`01_basics`.factorial
  val factorial2 = factorial(6)
  println(s"factorial2: $factorial2")

  // packages are in hierarchy, which should match hierarchy of the file system
  
  // package object - can access members by simple name anywhere in package
  //    members of package object are defined in file 'package.scala' within package directory
  sayHello
  println(s"speed of light: $SPEED_OF_LIGHT")

  // imports
  // import playground.PrinceCharming
  // import playground.Cinderella
  import playground.{Cinderella, PrinceCharming}  // import specific items
  // import playground._   // import everything from package
  import playground.{Cinderella => Princess}  // import aliasing

  val cinderella = Cinderella()
  val princeCharming = PrinceCharming()
  val princess = Princess()  

  // default imports -- accessable automatically
  //    java.lang - String, Object, Exception
  //    scala - Int, Nothing, Function ...
  //    scala.Predef - println, ???

}
