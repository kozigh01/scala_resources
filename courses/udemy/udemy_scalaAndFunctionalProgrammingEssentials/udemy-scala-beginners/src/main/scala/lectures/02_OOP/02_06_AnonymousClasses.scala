package lectures.`02_OOP`

object AnonymousClasses extends App:
  println("---------- Anonymous Classes ----------")

  trait Animal:
    def eat: Unit

  // or 
  // abstract Animal:
  //   def eat: Unit

  // creating an anonymous class
  val funnyAnimal: Animal = new Animal:
    override def eat: Unit = println("ha ha ha ha")
  /*  
    Equivalent to:

    class AnonyousClasses$$anon$1 extends Animal:
      override def eat: Unit = println("ha ha ha ha")
    val funnyAnimal: Animal = AnonyousClasses$$anon$1()
  */

  println(funnyAnimal.getClass())
  println(funnyAnimal.eat)
  funnyAnimal.eat

  class Person(name: String):
    def sayHi: String = s"Hi, my name is $name"

  // can create anonymous class implementations for classe as well as abstract classes and traits
  val jim: Person = new Person("Jim"):
    override def sayHi: String = "Hi there, I'm Jim"
  println(jim.sayHi)

end AnonymousClasses