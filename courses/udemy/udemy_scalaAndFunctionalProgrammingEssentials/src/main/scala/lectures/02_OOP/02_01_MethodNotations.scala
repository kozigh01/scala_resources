package lectures.`02_OOP`

object `02_01_MethodNotations` extends App {
  class Person(val name: String, favoriteMovie: String, val age: Int = 0):
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = 
      s"${this.name} is hanging out with ${person.name}"
    def isAlive: Boolean = true
    
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(times: Int): String = s"$name watched $favoriteMovie $times times"
    
    // can use special characters in method names
    def +(nickName: String): Person = Person(s"$name ($nickName)", favoriteMovie, age)    
    def *>(person: Person): String =   // the "hang out with" operator
      s"${this.name} is hanging out with ${person.name}"
    
    def unary_! : String = s"$name!!!"
    def unary_+ : Person = Person(name, favoriteMovie, age+1)

    def learns(topic: String): String = s"$name learns $topic"
    def learnsScala: String = this learns "Scala"

  val mary = this.Person("Mary", "Inception")
  println(s"Mary likes 'Inception': ${mary.likes("Inception")}")
  println(s"Mary likes 'What about Bob?': ${mary.likes("What about Bob?")}")
  
  // infix notation (or operator notation): works with methods that have 1 parameter
  println(s"Mary likes 'Inception': ${mary likes "Inception"}")

  val bob = this.Person("Bob", "What about Bob?")
  println(mary hangOutWith bob)   // using infix notation
  println(mary *> bob)   // using infix notation
  println(mary.*>(bob))  // equivalent to the line previous

  // all operators are actually methods in Scala
  println(5.+(4))

  // prefix notation: unary_prefix only works with - + ~ ! 
  val x = -1    // equivalent to 1.unary_-
  val y = 1.unary_-
  println(s"x = $x, y = $y")
  println(!bob)

  // postfix notation: applicable for functions that do not recieve parameters
  import scala.language.postfixOps
  println(mary.isAlive)
  println(mary isAlive)  // postfix notation - not really used that much

  // apply:
  println(mary.apply())
  println(mary())   // equivalent to mary.apply() - can call mary as a funtion


  // Exercises
  println((mary + "the rockstar")())
  println(s"${mary.name} is ${mary.age} years old")
  val mary2: Person = +mary
  println(s"${mary2.name} is ${mary2.age} years old")
  println(s"${mary.learns("cool scala stuff")}")
  println(s"${mary learnsScala}")
  println(mary(4))
}
