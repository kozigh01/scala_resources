package exercises

import exercises.MyListNonEmpty
import scala.annotation.tailrec

trait MyPredicate4[-T]:
  def test(el: T): Boolean

trait MyTransformer[-A, B]:
  def transform(el: A): B

abstract class MyList4[+T]:
  def head(): T
  def tail(): MyList4[T]
  def isEmpty(): Boolean
  def add[U >: T](el: U): MyList4[U]
  def printElements(): String
  override def toString(): String = s"[${printElements()}]"
  def map[U](transformer: MyTransformer[T, U]): MyList4[U]
  def flatMap[U](transformer: MyTransformer[T, MyList4[U]]): MyList4[U]
  def filter(predicate: MyPredicate4[T]): MyList4[T]
  def ++[B >: T](list: MyList4[B]): MyList4[B]

class MyList4Empty extends MyList4[Nothing]:
  def head(): Nothing = throw new NoSuchElementException
  def tail(): MyList4[Nothing] = throw new NoSuchElementException
  def isEmpty(): Boolean = true
  def add[U >: Nothing](n: U): MyList4[U] = MyList4NonEmpty(n, this)
  
  def ++[B >: Nothing](list: MyList4[B]): MyList4[B] = list
  override def printElements(): String = ""
  def map[U >: Nothing](transformer: MyTransformer[Nothing, U]): MyList4[U] = MyList4Empty()
  def flatMap[U](transformer: MyTransformer[Nothing, MyList4[U]]): MyList4[U] = MyList4Empty()
  def filter(prediCat4e: MyPredicate4[Nothing]): MyList4[Nothing] = MyList4Empty()

class MyList4NonEmpty[+T](val headVal: T, val tailVal: MyList4[T]) extends MyList4[T]:
  def head(): T = headVal
  def tail(): MyList4[T] = tailVal
  def isEmpty(): Boolean = false
  def add[U >: T](element: U): MyList4[U] = MyList4NonEmpty[U](element, this)  
  def ++[B >: T](list: MyList4[B]): MyList4[B] =
    isEmpty() match
      case true => list
      case false =>
        MyList4NonEmpty(head(), tail() ++ list)    
  override def printElements(): String = 
    this.isEmpty() match
      case true => ""
      case false => s"${this.tail().printElements()} ${this.head()}" 
  def map[U](transformer: MyTransformer[T, U]): MyList4[U] =
    this.isEmpty() match
      case true => MyList4Empty()
      case false =>
        val newHead = transformer.transform(this.head()) 
        MyList4NonEmpty(newHead, tail().map(transformer))
  def flatMap[U](transformer: MyTransformer[T, MyList4[U]]): MyList4[U] =
    this.isEmpty() match
      case true => MyList4Empty()
      case false =>
        transformer.transform(head()) ++ tail().flatMap(transformer)
  def filter(predicate: MyPredicate4[T]): MyList4[T] = 
    (this.isEmpty(), predicate.test(this.headVal)) match
      case (true, _) => MyList4Empty()
      case (false, test) =>
        if test then
          new MyList4NonEmpty(this.head(), tail().filter(predicate))  
        else
          tail().filter(predicate)

class Animal4(val name: String):
  override def toString(): String = s"I am an Animal4 named '$name'"
class Dog4(name: String) extends  Animal4(name):
  override def toString(): String = s"I am a Dog4 named '$name'"
class Cat4(name: String) extends  Animal4(name):
  override def toString(): String = s"I am a Cat4 named '$name'"
class Bird4(name: String) extends  Animal4(name):
  override def toString(): String = s"I am a Bird4 named '$name'"


@main def myListCovarianceWithTraits() =
  val mylist: MyList4[Cat4] = MyList4Empty()
  println(s"mylist: ${mylist.toString()}")

  val cat4 = Cat4("boots")
  val mylist1 = mylist.add(cat4)
  println(s"mylist: ${mylist1.toString()}")

  val dog4 = Dog4("rusty")
  val mylist2 = mylist1.add(dog4)
  
  val bird4 = Bird4("tweety")
  val mylist3 = mylist2.add(bird4)
  println(mylist3.head().getClass())
  println(s"mylist: ${mylist3.toString()}")

  val transformer = new MyTransformer[Animal4, Bird4]:
    override def transform(el: Animal4): Bird4 = Bird4(el.name)
  val mylist4 = mylist3.map(transformer)
  println(mylist4.getClass())
  println(mylist4.head().getClass())
  println(s"mylist transform: ${mylist4.toString()}")
     
  val myFilter5 = new MyPredicate4[Animal4]:
    override def test(el: Animal4): Boolean = 
      val result = el.name.contains("tweety")
      result
  val mylist5 = mylist4.filter(myFilter5)
  println(s"mylist filtered: ${mylist5.toString()}")

  val tranformer2 = new MyTransformer[Animal4, MyList4[Animal4]]:
    override def transform(el: Animal4): exercises.MyList4[Animal4] = 
      val mylist: MyList4[Animal4] = MyList4Empty()
      mylist.add(el).add(el).add(el)
  val mylist6 = mylist3.flatMap(tranformer2)
  println(s"mylist flatMap: ${mylist6.toString()}")
  
  val mylistSting = MyList4NonEmpty[String]("!", MyList4NonEmpty("Scala", MyList4NonEmpty("Hello", MyList4Empty())))
  println(s"mylist: ${mylistSting.toString()}")
