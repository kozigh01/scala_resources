package lectures.`04_PatternMatching`

import scala.util.Random

object PatternMatching:
  def basics =
    println("----- Pattern Matching Basics -----\n")

    /*
      1. cases are matched in orderj
      2. return type of match is the unification of all the types for all the cases
      3. pattern matching works really well with case classes since they include extracter features
    */
    
    // switch on steriods
    val random = Random(System.nanoTime())
    val x = random.nextInt(10)
    val description = x match
      case 1 => "the one"
      case 2 => "double or nothing"
      case 3 => "third times a charm"
      case _ => "something else"  // _ = WILDCARD

    println(s"x: $x, description: $description")

    // 1. decompose values
    case class Person(name: String, age: Int)
    val bob = Person("bob", 20)
    val greeting = bob match
      case Person(n, a) => s"Hello, I am $n and I am $a years old"
      case null => "I don't know who I am"
    println(greeting)
    
    // can add "gaurds" for cases
    val random2 = Random(System.nanoTime())
    val bob2 = Person("bob2", random.nextInt(50))
    val greeting2 = bob2 match
      case Person(n, a) if a < 30 => s"Hello, I am $n and I am $a years old, I am a pup"
      case Person(n, a) => s"Hello, I am $n and I am $a years old, I am old"
      case null => "I don't know who I am"
    println(greeting2)

    // pattern matching on sealed hierarchies
    sealed class Animal
    case class Dog(breed: String) extends Animal
    case class Parrot(greeting: String) extends Animal

    val animal: Animal = Dog("Terra Nova")
    animal match
      case Dog(b) => println(s"Matched a dog of the breed $b")
      case Parrot(g) => println(s"Matched a parrot who says '$g'")
      case _ => println("you're an animal")
    
  def exercises =
    println("----- Pattern Matching Basics - Exercises -----\n")

    sealed trait Expr
    case class Number(n: Int) extends Expr
    case class Sum(el: Expr, e2: Expr) extends Expr
    case class Prod(el: Expr, e2: Expr) extends Expr

    def displayExpr(e: Expr): String =
      e match
        case Number(nbr) => nbr.toString()
        // case Sum(e1, e2) if e1.isInstanceOf[Prod] => s"${displayExpr(e1)} + (${displayExpr(e2)})"
        // case Sum(e1, e2) if e2.isInstanceOf[Prod] => s"(${displayExpr(e1)}) + ${displayExpr(e2)}"
        case Sum(e1, e2) => s"${displayExpr(e1)} + ${displayExpr(e2)}"
        case Prod(e1, e2) if e1.isInstanceOf[Sum] && e2.isInstanceOf[Sum] => s"(${displayExpr(e1)}) * (${displayExpr(e2)})"
        case Prod(e1, e2) if e1.isInstanceOf[Sum] => s"(${displayExpr(e1)}) * ${displayExpr(e2)}"
        case Prod(e1, e2) if e2.isInstanceOf[Sum] => s"${displayExpr(e1)} * (${displayExpr(e2)})"
        case Prod(e1, e2) => s"${displayExpr(e1)} * ${displayExpr(e2)}"

    // course solution
    def show(e: Expr): String =
      e match
        case Number(nbr) => s"$nbr"
        case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
        // case Prod(e1, e2) => 
        //   def maybeShowParentheses(exp: Expr): String = exp match
        //     case Prod(_, _) => show(exp)
        //     case Number(_) => show(exp)
        //     case _ => s"(${show(exp)})"
        //   s"${maybeShowParentheses(e1)} * ${maybeShowParentheses(e2)}}"          
        case Prod(e1, e2) if e1.isInstanceOf[Sum] && e2.isInstanceOf[Sum] => s"(${displayExpr(e1)}) * (${displayExpr(e2)})"
        case Prod(e1, e2) if e1.isInstanceOf[Sum] => s"(${displayExpr(e1)}) * ${displayExpr(e2)}"
        case Prod(e1, e2) if e2.isInstanceOf[Sum] => s"${displayExpr(e1)} * (${displayExpr(e2)})"
        case Prod(e1, e2) => s"${displayExpr(e1)} * ${displayExpr(e2)}"

    val expression1 = Sum(Number(2), Number(3))
    println(s"expression1.isInstanceOf[Sum]: ${expression1.isInstanceOf[Sum]}")
    println(s"expression1: ${displayExpr(expression1)}")

    val expression2 = Sum(Sum(Number(2), Number(3)), Number(4))
    println(s"expression2: ${displayExpr(expression2)}")

    val expression3 = Prod(Sum(Number(2), Number(1)), Number(3))
    println(s"expression3: ${displayExpr(expression3)}")
    val expression4 = Prod(Number(2), Sum(Number(1), Number(3)))
    println(s"expression4: ${displayExpr(expression4)}")
    val expression4a = Prod(Sum(Number(2), Number(4)), Sum(Number(1), Number(3)))
    println(s"expression4a: ${displayExpr(expression4a)}")

    val expression5 = Sum(Prod(Number(2), Number(1)), Number(3))
    println(s"expression5: ${displayExpr(expression5)}")
    val expression6 = Sum(Number(2), Prod(Number(1), Number(3)))
    println(s"expression6: ${displayExpr(expression6)}")
      


@main def patterMatchingBasics =
  println("---------- Pattern Matching ----------")
  
  // PatternMatching.basics
  PatternMatching.exercises
  
  println("\n---------- End Pattern Matching ----------")
