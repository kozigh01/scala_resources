package lectures.`02_OOP`

object AbstractDataTypes extends App:

  abstract class Animal:
    // abstract members - don't have implementation which must be provided by derived class
    val creatureType: String
    def eat: Unit

  class Dog extends Animal:
    val creatureType: String = "Canine"
    def eat: Unit = println("chomp, chomp, chomp")

  //  Traits (ultimate abstract types)
  trait Carnivore:
    val preferredMeal: String = "Wildabeast"
    def eat(animal: Animal): Unit

  trait ColdBlooded

  class Crocodile extends Animal, Carnivore, ColdBlooded:
    val creatureType: String = "Reptile"
    def eat: Unit = println("crush, crack, swollow")

    def eat(animal: Animal): Unit = println(s"I'm eating a ${animal.creatureType}")

  println("---------- Abstract Data Types ----------\n")

  // val animal = new Animal   // throws error: can't instanciate an abstract class
  val dog = Dog()
  dog.eat

  val croc = Crocodile()
  println(s"\nA crocodile's favoite meal: ${croc.preferredMeal}")
  croc.eat
  croc.eat(dog)

  // traits vs abstract classes
  //    1. traits do not have constructor parameters
  //    2. class can implement multiple traits 
  //    3. class can only inherit from one abstract class (single class inheritance)
  //    4. traits = "behavior" (what they do) while abstract  class = "thing"