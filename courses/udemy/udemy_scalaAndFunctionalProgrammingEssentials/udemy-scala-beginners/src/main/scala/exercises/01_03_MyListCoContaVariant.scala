package exercises


abstract class MyList3[+T]:
  def head(): T
  def tail(): MyList3[T]
  def isEmpty(): Boolean
  def add[U >: T](el: U): MyList3[U]
  def printElements(): String
  override def toString(): String = s"[${printElements()}]"

class MyList3Empty extends MyList3[Nothing]:
  def head(): Nothing = throw new NoSuchElementException
  def tail(): MyList3[Nothing] = throw new NoSuchElementException
  def isEmpty(): Boolean = true
  def add[U >: Nothing](n: U): MyList3[U] = MyList3NonEmpty(n, this)
  override def printElements(): String = ""

class MyList3NonEmpty[+T](val headVal: T, val tailVal: MyList3[T]) extends MyList3[T]:
  def head(): T = headVal
  def tail(): MyList3[T] = tailVal
  def isEmpty(): Boolean = false
  def add[U >: T](element: U): MyList3[U] = MyList3NonEmpty[U](element, this)
  override def printElements(): String = 
    this.isEmpty() match
      case true => ""
      case false => s"${this.tail().printElements()} ${this.head()}" 

class Animal(val name: String):
  override def toString(): String = s"I am an Animal named '$name'"
class Dog(name: String) extends  Animal(name):
  override def toString(): String = s"I am a Dog named '$name'"
class Cat(name: String) extends  Animal(name):
  override def toString(): String = s"I am a Cat named '$name'"
class Bird(name: String) extends  Animal(name):
  override def toString(): String = s"I am a Bird named '$name'"

@main def myListCovariance() =
  val mylist: MyList3[Cat] = MyList3Empty()
  println(s"mylist: ${mylist.toString()}")

  val cat = Cat("boots")
  val mylist1 = mylist.add(cat)
  println(s"mylist: ${mylist1.toString()}")
  
  val dog = Dog("rusty")
  val mylist2 = mylist1.add(dog)
  println(s"mylist: ${mylist2.toString()}")
  
  val bird = Bird("tweety")
  val mylist3 = mylist2.add(bird)
  println(mylist3.head().getClass())
  println(s"mylist: ${mylist3.toString()}")
  
  val mylistSting = MyList3NonEmpty[String]("!", MyList3NonEmpty("Scala", MyList3NonEmpty("Hello", MyList3Empty())))
  println(s"mylist: ${mylistSting.toString()}")
