package exercises

abstract class MyListGeneric[+T]:
  def head(): T
  def tail(): MyListGeneric[T]
  def isEmpty(): Boolean
  def add[U >: T](el: U): MyListGeneric[U]
  def printElements(): String
  override def toString(): String = s"[${printElements()}]"

class MyListGenericEmpty[Nothing] extends MyListGeneric[Nothing]:
  def head(): Nothing = throw new NoSuchElementException
  def tail(): MyListGeneric[Nothing] = throw new NoSuchElementException
  def isEmpty(): Boolean = true
  def add[T >: Nothing](n: T): MyListGeneric[T] = MyListGenericNonEmpty(n, this)
  override def printElements(): String = ""

class MyListGenericNonEmpty[+T](val headVal: T, val tailVal: MyListGeneric[T]) extends MyListGeneric[T]:
  def head(): T = headVal
  def tail(): MyListGeneric[T] = tailVal
  def isEmpty(): Boolean = false
  def add[U >: T](n: U): MyListGeneric[U] = MyListGenericNonEmpty[U](n, this)
  override def printElements(): String = 
    this.isEmpty() match
      case true => ""
      case false => s"${this.tail().printElements()} ${this.head()}" 

@main def myListGeneric() =
  val mylist = MyListGenericEmpty()
  println(s"mylist: ${mylist.toString()}")

  val mylist1 = mylist.add("111")
  println(s"mylist: ${mylist1.toString()}")

  val mylist2 = mylist1.add("222")
  println(s"mylist: ${mylist2.toString()}")
  
  val mylist3 = MyListGenericNonEmpty[String]("!", MyListGenericNonEmpty("Scala", MyListGenericNonEmpty("Hello", MyListGenericEmpty())))
  println(s"mylist: ${mylist3.toString()}")
