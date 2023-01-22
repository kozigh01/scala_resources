package lectures.`02_OOP`

object Inheritance extends App:

  sealed class Animal:
    val creatureType = "wild"
    protected def eat: Unit = println("nomnomnom")
    def sleep: Unit = println("zzzzzzzzzzzz")
    final def run: Unit = println("I am running")

  // Scala has single class inheritance
  class Cat extends Animal:
    val numberOfLegs = 4

    def crunch: Unit =
      eat
      println("crunch crunch")


  println("---------- Inheritance ----------\n")

  val cat = Cat()  
  println(s"cat creatureType: ${cat.creatureType}")
  cat.crunch

  val animal = Animal()
  // animal.eat    // eat not accessible - protected only visible inside class and subclasses


  // constructors
  class Person(name: String, age: Int):
    def this(name: String) = this(name, 0)

  class Adult(name: String, age: Int, IdCard: String) extends Person(name)
  // class Adult(name: String, age: Int, IdCard: String) extends Person(name, age)


  // overriding methods
  class Dog extends Animal:
    override val creatureType = "domestic"
    override def eat: Unit =
      super.eat
      println("yum, yum")

  class Hamster(override val creatureType: String) extends Animal:
    override def eat: Unit = println("nibble, nibble")
    override def sleep: Unit = println("snort, snort, wheeze")
    // override def run: Unit = println("I am shuffling")  // can't override - method is declared "final"

  val dog = Dog()
  println(s"\ndog creatureType: ${dog.creatureType}")
  dog.eat

  val hamster = Hamster("rodent")
  println(s"\nhampster creatureType: ${hamster.creatureType}")


  // type substitution (polymorphism)
  val anAnimal: Animal = Hamster("pet")
  println(s"\nanAnimal creatureType: ${anAnimal.creatureType}")
  anAnimal.sleep  // calls the overridden sleep method, due to polymorphism (type substitution)


  // super
  println("\neat using call to super.eat")
  dog.eat


  // preventing overrides
  //    1. use keyword final on a member of a class
  //    2. use keyword final on the class as a whole
  //    3. use the keyword sealed on the class - can extend classes in this file, but not from other files


end Inheritance
