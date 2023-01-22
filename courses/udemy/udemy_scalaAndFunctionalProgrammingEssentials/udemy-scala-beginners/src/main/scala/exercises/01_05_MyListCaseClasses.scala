package exercises

import exercises.MyListNonEmpty
import scala.annotation.tailrec

trait MyPredicate5[-T]:
  def test(el: T): Boolean

trait MyTransformer5[-A, B]:
  def transform(el: A): B

abstract class MyList5[+T]:
  def head(): T
  def tail(): MyList5[T]
  def isEmpty(): Boolean
  def add[U >: T](el: U): MyList5[U]
  def printElements(): String
  override def toString(): String = s"[${printElements()}]"
  def map[U](transformer: MyTransformer5[T, U]): MyList5[U]
  def flatMap[U](transformer: MyTransformer5[T, MyList5[U]]): MyList5[U]
  def filter(predicate: MyPredicate5[T]): MyList5[T]
  def ++[B >: T](list: MyList5[B]): MyList5[B]

case class MyList5Empty() extends MyList5[Nothing]:
  def head(): Nothing = throw new NoSuchElementException
  def tail(): MyList5[Nothing] = throw new NoSuchElementException
  def isEmpty(): Boolean = true
  def add[U >: Nothing](n: U): MyList5[U] = MyList5NonEmpty(n, this)
  
  def ++[B >: Nothing](list: MyList5[B]): MyList5[B] = list
  override def printElements(): String = ""
  def map[U >: Nothing](transformer: MyTransformer5[Nothing, U]): MyList5[U] = MyList5Empty()
  def flatMap[U](transformer: MyTransformer5[Nothing, MyList5[U]]): MyList5[U] = MyList5Empty()
  def filter(prediCat5e: MyPredicate5[Nothing]): MyList5[Nothing] = MyList5Empty()

case class MyList5NonEmpty[+T](val headVal: T, val tailVal: MyList5[T]) extends MyList5[T]:
  def head(): T = headVal
  def tail(): MyList5[T] = tailVal
  def isEmpty(): Boolean = false
  def add[U >: T](element: U): MyList5[U] = MyList5NonEmpty[U](element, this)  
  def ++[B >: T](list: MyList5[B]): MyList5[B] =
    isEmpty() match
      case true => list
      case false =>
        MyList5NonEmpty(head(), tail() ++ list)    
  override def printElements(): String = 
    this.isEmpty() match
      case true => ""
      case false => s"${this.tail().printElements()} ${this.head()}" 
  def map[U](transformer: MyTransformer5[T, U]): MyList5[U] =
    this.isEmpty() match
      case true => MyList5Empty()
      case false =>
        val newHead = transformer.transform(this.head()) 
        MyList5NonEmpty(newHead, tail().map(transformer))
  def flatMap[U](transformer: MyTransformer5[T, MyList5[U]]): MyList5[U] =
    this.isEmpty() match
      case true => MyList5Empty()
      case false =>
        transformer.transform(head()) ++ tail().flatMap(transformer)
  def filter(predicate: MyPredicate5[T]): MyList5[T] = 
    (this.isEmpty(), predicate.test(this.headVal)) match
      case (true, _) => MyList5Empty()
      case (false, test) =>
        if test then
          new MyList5NonEmpty(this.head(), tail().filter(predicate))  
        else
          tail().filter(predicate)

class Animal5(val name: String):
  override def toString(): String = s"I am an Animal5 named '$name'"
class Dog5(name: String) extends  Animal5(name):
  override def toString(): String = s"I am a Dog5 named '$name'"
class Cat5(name: String) extends  Animal5(name):
  override def toString(): String = s"I am a Cat5 named '$name'"
class Bird5(name: String) extends  Animal5(name):
  override def toString(): String = s"I am a Bird5 named '$name'"


@main def myListCaseClasses() =
  val mylist: MyList5[Cat5] = MyList5Empty()
  println(s"mylist: ${mylist.toString()}")

  val cat5 = Cat5("boots")
  val mylist1 = mylist.add(cat5)
  println(s"mylist: ${mylist1.toString()}")

  val dog5 = Dog5("rusty")
  val mylist2 = mylist1.add(dog5)
  
  val bird5 = Bird5("tweety")
  val mylist3 = mylist2.add(bird5)
  println(mylist3.head().getClass())
  println(s"mylist: ${mylist3.toString()}")

  val transformer = new MyTransformer5[Animal5, Bird5]:
    override def transform(el: Animal5): Bird5 = Bird5(el.name)
  val MyList5 = mylist3.map(transformer)
  println(MyList5.getClass())
  println(MyList5.head().getClass())
  println(s"mylist transform: ${MyList5.toString()}")
     
  val myFilter5 = new MyPredicate5[Animal5]:
    override def test(el: Animal5): Boolean = 
      val result = el.name.contains("tweety")
      result
  val mylist5 = MyList5.filter(myFilter5)
  println(s"mylist filtered: ${mylist5.toString()}")

  val tranformer2 = new MyTransformer5[Animal5, MyList5[Animal5]]:
    override def transform(el: Animal5): MyList5[Animal5] = 
      val mylist: MyList5[Animal5] = MyList5Empty()
      mylist.add(el).add(el).add(el)
  val mylist6 = mylist3.flatMap(tranformer2)
  println(s"mylist flatMap: ${mylist6.toString()}")
  
  val myListString = MyList5NonEmpty[String]("!", MyList5NonEmpty("Scala", MyList5NonEmpty("Hello", MyList5Empty())))
  println(s"mylist: ${myListString.toString()}")

  val myListStringClone = MyList5NonEmpty[String]("!", MyList5NonEmpty("Scala", MyList5NonEmpty("Hello", MyList5Empty())))
  println(s"myListString = myListStringClone: ${myListString == myListStringClone}")

