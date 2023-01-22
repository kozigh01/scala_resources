package exercises

import scala.annotation.tailrec

abstract class MyList :
  def head(): Int
  def tail(): MyList
  def isEmpty(): Boolean
  def add(n: Int): MyList
  def printElements(): String
  override def toString(): String = s"[${printElements()}]"

class MyListEmpty extends MyList:
  def head(): Int = throw new NoSuchElementException
  def tail(): MyList = throw new NoSuchElementException
  def isEmpty(): Boolean = true
  def add(n: Int): MyList = MyListNonEmpty(n, this)
  override def printElements(): String = ""
  

class MyListNonEmpty(val headVal: Int = 0, val tailVal: MyList  = null) extends MyList:
  def head(): Int = headVal
  def tail(): MyList = tailVal
  def isEmpty(): Boolean = false
  def add(n: Int): MyList = MyListNonEmpty(n, this)
  override def printElements(): String = 
    this.isEmpty() match
      case true => ""
      case false => s"${this.tail().printElements()} ${this.head()}" 
    
  // override def toString(): String =
  //   @tailrec
  //   def helper(list: MyList, accum: String): String =
  //     list.isEmpty() match
  //       case true => accum
  //       case false => helper(list.tail(), s"${list.head()} $accum")

  //  helper(this, "")


@main def myList(): Unit =
  val mylist: MyList = MyListEmpty()
  println(s"mylist: ${mylist.toString()}")

  val mylist1: MyList = mylist.add(11)
  println(s"mylist: ${mylist1.toString()}")

  val mylist2: MyList = mylist1.add(22)
  println(s"mylist: ${mylist2.toString()}")
  
  val mylist3: MyList = MyListNonEmpty(3, MyListNonEmpty(2, MyListNonEmpty(1, MyListEmpty())))
  println(s"mylist: ${mylist3.toString()}")