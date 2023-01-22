package lectures.`02_OOP`

object `02_01_OOPBasics` extends App {

  //  Class parameters are not class members automatically (use val/var to make a member)
  class Person(name: String, val age: Int = 0): // constructor
    val x = name.length   // define a class field

    println("running in 'Person' definition") // runs on each instantiation of 'Person'

    // method overloading is allowed
    def greet(name: String): Unit = println(s"${this.name} says: Hi $name")
    def greet(): Unit = println(s"Hi, I am $name")

    // multiple constructors
    //    not that useful - implimentation can olny call other constructor: easier to use default values in main constructor
    // def this(name: String) = this(name, 0)


  // Exercises
  class Writer(firstName: String, lastName: String, val yearOfBirth: Int):
    def fullName(): String = s"$firstName $lastName"

  class Novel(val name: String, releaseYear: Int, val author: Writer):
    def authorAge: Int = java.time.Year.now.getValue - author.yearOfBirth
    def isWrittenBy(author: Writer): Boolean = author.fullName() == this.author.fullName()
    def copy(year: Int): Novel = Novel(name, year, author)

  class Counter(val count: Int):
    def increment(): Counter = Counter(count+1)
    def decrement(): Counter = Counter(count-1)

    def increment(step: Int): Counter = Counter(count+step)
    def decrement(step: Int): Counter = Counter(count-step)

  val person = Person("John", 26)
  println(person.age)
  println(person.x)
  // println(person.name)  // throws compiler error - name is not a class field
  person.greet("Daniel")
  person.greet()

  val bob = Writer("Bob", "Bobbins", 1993)
  val novel = Novel("a great book", 2017, bob)
  println(s"${novel.author.fullName()} has written '${novel.name}' when they were ${novel.authorAge} years old }")

  val novelNew = novel.copy(2021)
  println(novelNew)

  val counter1 = Counter(11)
  val counter1Inc = counter1.increment()
  val counter1Dec = counter1.decrement()
  println(s"Counter count: ${counter1.count}")
  println(s"Counter incremented count: ${counter1Inc.count}")
  println(s"Counter decremented count: ${counter1Dec.count}")
  println(s"counter1 == counter1Inc: ${counter1 == counter1Inc}")
  println(s"Counter incremented (step 3) count: ${counter1.increment(3).count}")
  println(s"Counter decremented (step 3) count: ${counter1.decrement(3).count}")
}
