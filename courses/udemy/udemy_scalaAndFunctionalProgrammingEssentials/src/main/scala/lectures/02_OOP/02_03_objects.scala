package lectures.`02_OOP`

//  Scala does not have class level functionality (no class "static")
//    instead Scala uses "Object" (and companion Objects)

//  Scala object is a singleton instance (and a type)
object Objects:

  object Person:
    val N_EYES = 2

    def canFly: Boolean = false

    // companion objects often times have factory methods for their companion class
    def fromParents(mother: Person, father: Person): Person = new Person("bobbie")

    // can also use apply for factory methods for nicer syntax
    def apply(mother: Person, father: Person): Person = new Person("bobbie")
    def apply(name: String): Person = new Person(name)  // added this to avoid "new" when creating Person with name

  // class Person and object Person are "companions" (same scope and same name)
  //    class Person is a companion class
  class Person(val name: String):
    def greeting() = s"Hi, I am $name"


  // Use this if object "Objects" does not extend "App" to make runnable
  def main(args: Array[String]): Unit =
    println("---------- Ojects ----------")

    // objects are singletons
    var mary = Person
    var john = Person
    println(s"mary == john: ${mary == john}")

    println(s"\nPerson has ${Person.N_EYES} eyes")
    println(s"Person can fly: ${Person.canFly}")  

    // class instances are not singletons
    var mary2 = Person("mary")
    var john2 = Person("john")
    println(s"mary2 == john2: ${mary2 == john2}")

    // companion object factory
    var bobbie2 = Person.fromParents(mary2, john2)
    println(bobbie2.greeting())
    var bobbie2_1 = Person(mary2, john2)
    println(bobbie2_1.greeting())
