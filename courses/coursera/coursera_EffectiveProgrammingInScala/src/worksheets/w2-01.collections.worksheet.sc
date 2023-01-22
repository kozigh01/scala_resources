/**
    * Lists
    */
case class AddressBook(contacts: List[Contact])
case class Contact(name: String, email: String, phoneNumbers: List[String])

val alice = Contact("Alice", "alice@alice.com", List())
val bob = Contact("Bob", "bob@bob.com", List("+41787829420"))

val addressBook = AddressBook(List(alice, bob))

val fruits = List("apples", "oranges", "pears")
val nums = List(1, 2, 3, 4, 5)
val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
val empty = List()

val numberOfContacts = addressBook.contacts.size
val isAliceInContacts = addressBook.contacts.contains(alice)
val contactNames = addressBook.contacts.map(_.name)
val contactNames2 = addressBook.contacts.map(c => c.name)
val contactsWithPhone = addressBook.contacts.filter(_.phoneNumbers.nonEmpty)

val contacts1 = List(alice, bob)
val contacts1_1 = alice :: (bob :: Nil)
val contacts1_2 = Nil.::(bob).::(alice)
val carol = Contact("Carol", "carol@carol.com", List())
val contacts2 = carol :: addressBook.contacts
val contacts3 = addressBook.contacts.::(carol)
contacts2.size
carol :: addressBook.contacts
carol +: addressBook.contacts

addressBook.contacts match
  case contact :: tail => contact.name
  case Nil => "No contact"
addressBook.contacts match
  case first :: second :: Nil => second.name
  case _ => "List doesn't have two elements"

fruits.head
fruits.tail
fruits.tail.head
fruits.tail.tail.head
// fruits.tail.tail.tail.head // throws error
fruits(0)
// fruits(4) // throws error


/**
 * Functions
 */
val increment: Int => Int = 
  x =>
    val result = x + 1
    result

val add = (x: Int, y: Int) => x + y
add(1, increment(2))


/**
  * Collection Construction and Tuples
  */
import scala.collection.mutable

List.empty
mutable.ArrayBuffer.empty
Map.empty

val listEmpty = List.empty[Int]
val arrayBfferEmpty = mutable.ArrayBuffer.empty[Double]
val mapEmpty = Map.empty[String, Boolean]

val list1 = List(1,2,3,4)
val arrayBuffer1 = mutable.ArrayBuffer("a","b","c")
val tuple1 = ("a",true)
val tuple2 = "b" -> false
val map1 = Map(tuple1, tuple2, "c" -> false)
tuple1(0)
tuple1(1)
val (k, v) = tuple1
tuple1 match
  case (k, v) => s"tuple 1: k = '$k', v = $v"

//  Adding elements to front/back of collection
val list2 = 0 +: list1 :+ 5
list1
"0" +: arrayBuffer1 :+ "d"
arrayBuffer1
val map2 = map1 + ("d" -> true)
map1


/**
  * Querying Collections and Option
  *   Many more than those shown see: https://www.scala-lang.org/api/3.2.0/scala/collection/immutable.html
  */
val list1_2 = List(1,2,3,4,5)
list1_2.isEmpty
list1_2.nonEmpty
list1_2.find(x => x % 2 == 0)
list1_2.findLast(x => x %2 == 0)
list1_2.filter(x => x % 2 == 0)
list1_2.contains(4)
list1_2.contains(33)
list1_2.last

// option
list1_2.find(x => x % 2 == 0) match
  case Some(x) => s"Found the element = $x"
  case None => "Didn't find the element"
list1_2.find(x => x == 33) match
  case Some(x) => s"Found the element = $x"
  case None => "Didn't find the element"


/**
  * Transforming Collections
  */

// Map
val list1_3 = List(1,2,3,4)
list1_3.map(x => x + 1)
list1_3
val ab1_3 = mutable.ArrayBuffer(1,2,3,4)
ab1_3.map(x => x %2 == 0)
ab1_3
val map1_3 =Map(0 -> "No", 1 -> "Yes")
map1_3.map((k,v) => k -> (v * 2))
map1_3

// FlatMap
List(1,2,3).flatMap(x => List())
mutable.ArrayBuffer(1,2,3).flatMap(x => mutable.ArrayBuffer(x, x*5))
Map(0 -> "zero", 1 -> "one").flatMap((key, value) => Map(key.toString() -> key))

case class Contact2(name: String, phoneNbrs: List[String])
val contacts = List(Contact2("bob", List("1234","5678")), Contact2("abby", List("9876", "5432")))
contacts.flatMap(x => x.phoneNbrs)

// FoldLeft (like "reduce" in other languages)
List(1,2,3).foldLeft(0)((accum, el) => accum + el)
List(1,2,3).foldLeft("")((accum, el) => accum + el.toString())
List(1,2,3).foldLeft(List.empty[Int])((accum, el) => el +: accum)
List(1,2,3).foldLeft(true)((accum, el) => el % 2 == 0)

mutable.ArrayBuffer(1,2,3,4).foldLeft(1)((accum, el) => accum * el)

Map(0 -> "zero", 1 -> "one").foldLeft("")((accum, el) => accum + s"${el(0)} = ${el(1)}, ")

// GroupBy
val emails = List("alice@sca.la", "bob@sca.la", "carol@earh.world")
val domain: String => String = 
  email => email.dropWhile(c => c!= '@').drop(1)
val emailsByDomain = emails.groupBy(domain)
emailsByDomain


/**
 * Sequences and maps - unique capabilities
 */
val ab1_4 = mutable.ArrayBuffer(1,2,3)
ab1_4.head
ab1_4.tail

List(5,2,3,1,4).sortBy(x => x)

val list1_4 = List("Alice" -> 42, "Bob" -> 30, "Werner" -> 77, "Owl" -> 6)
list1_4.sortBy((_, age) => age)
list1_4.
sortBy((name, _) => name)

val map1_4 = Map("a" -> 0, "b" -> 1, "c" -> 2)
map1_4.get("a")
map1_4.get("Me")


/**
  * Option
  */
case class Contact3(name: String, maybeEmail: Option[String])
val alice3 = Contact3("Alice", Some("alice@sca.la"))
val bob3 = Contact3("Bob", None)
val carol3 = Contact3("Carol", Some("carol@sca.la"))
def hasEmail(contact: Contact3) =
  contact.maybeEmail match
    case Some(email) => s"I have an email"
    case None => "No email for me"
hasEmail(alice3)
hasEmail(bob3)

alice3.maybeEmail.map(c => c.size).getOrElse(0)
bob3.maybeEmail.map(c => c.size).getOrElse(0)

alice3.maybeEmail.zip(bob3.maybeEmail)
alice3.maybeEmail.zip(carol3.maybeEmail)

// List[Int]().head // throws exception
List[Int]().headOption
List(1,2,3).headOption


/**
  * Collection Extras
  */
var listConcat = List(1,2,3) ++ List(4,5,6)
var mapContat = Map("a" -> 1, "b" -> 2).++(Map("c" -> 3, "d" -> 4))

var ab1_5 = mutable.ArrayBuffer("a","b")
var ab2_5 = mutable.ArrayBuffer("c","d")
var abConcat = ab1_5 ++ ab2_5

// mutable actions
ab1_5 ++= ab2_5  // mutates the first operand
"z" +=: ab1_5
val ab1_7 = mutable.ArrayBuffer("a","b","c")
ab1_7 += "y"
ab1_7 += "d"
ab1_7 -= "c"
mutable.ArrayBuffer(1,2,3,3,4,5) -= 3
mutable.ArrayBuffer(1,2,3,3,4,5) --= mutable.ArrayBuffer(2,4)
mutable.ArrayBuffer(1,2,3,3,4,5) --= List(2,4)

List(1,2,3,4).exists(x => x < 0)
List(1,2,3,4).exists(x => x < 2)
List(1,2,3,4).forall(x => x % 2 == 0)
List(2,4,6,8).forall(x => x % 2 == 0)

ab1_7(0)
ab1_7(2)

// mutable map
val map1_5 = mutable.HashMap.empty[String, Int]
map1_5 += ("a" -> 0)
map1_5 += ("b" -> 1)
map1_5 += ("b" -> 2)
map1_5 += ("c" -> 3)


