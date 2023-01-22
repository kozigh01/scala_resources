package lectures.`02_OOP`

object Generics extends App:
  println("---------- Generics ----------")

  class MyList[A]
    // use type parameter A inside class

  // MyList companion object
  object MyList:
    // generic methods
    def empty[A]: MyList[A] = ???

  class MyMap[Key, Value]


  val listOfInt = MyList[Int]
  val emptyListOfInt = MyList.empty[Int]
  val listOfString = MyList[String]

  val myMap1 = MyMap[Int, String]


  // variance problem: does list of Cat or Dog extend list of Animal?
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1) yes - Mylist[Cat] extends Mylist[Animal] => COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val listOfAnimal: CovariantList[Animal] = new CovariantList[Cat]
  // question: can we add a different type of animal - listOfAnimal.add(Dog)

  // 2) no - MyList[Cat] does not extend MyList[Animal]
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = InvariantList[Animal]  // type parameters must match
  // val invariantAnimalLit: InvariantList[Animal] = InvariantList[Cat]  // throws an error

  //  3: Hell No!!  CONTRAVARIANCE  (warning, this is very contra-intuitive)
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = ContravariantList[Animal]

  class Trainer[-A]
  val trainer: Trainer[Cat] = Trainer[Animal]


  //  bounded types  upper bounded (is subtype of) use '<:', lower bounded (is supertype of) use '>:'
  //    bounded types help solve problem when using Covariant types
  class Cage[A <: Animal](animal: A)   // class Cage only accepts type parameters that are sub-types of Animal
  val cage = Cage(Dog())
  class Car
  // val cage2 = Cage(Car())  // won't compile, since Car is not a subtype of Animal

  class MyListCovariant[+A]:
    // def add(element: A): MyListCovariant[A] = ???   //   doesn't compile (hover to see error
    
    // adding an instance of a superclass (Dog -> Animal for example) turns list into list super class
    def add[B >: A](element: B): MyListCovariant[B] = ??? 

  val listCovariant = MyListCovariant[Cat]()    // is MyListCovariant[Cat]
  val listCovariant2 = listCovariant.add(Dog())   // is now a MyListCovariant[Animal]


  // Exercise - make MyList from previous section into a Generic
  //    see: exercises -> 01_02_MyListGeneric
  