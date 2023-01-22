package lectures.`02_OOP`

object CaseClasses extends App:
  /*
    Case classes provide a class amd a companion class behind the scenes
    Case classes provide: equals, hashCode, toString 

    1. class parameters are automatically promoted to fields
    2. adds a sensible toString: jim.toString is equavalent to just jim()
    3. equals and hashCode are automaticall implemented (good for collections)
    4. have handy copy method whch take "overriding" named parameters
    5. case classes have companion objects
    6. case classes are serializable
    7. case classes have extractor patterns, can be used in pattern matching
  */
  
  case class Person(name: String, age: Int)

  val jim: Person = Person("jim", 34)
  println(s"jim: $jim (name: ${jim.name}, age: ${jim.age})")
  println(jim.toString())

  val jim2 = Person("jim", 34)
  println(s"jim == jim2: ${jim == jim2}")
  
  val jim3 = jim.copy()
  println(s"jim == jim3: ${jim == jim3}")
  
  val jim4 = jim.copy(age = 35)
  println(s"jim4: $jim4")
  println(s"jim == jim4: ${jim == jim4}")

  case object UnitedKingdom:
    def name: String = "The UK of GB adn NI"


  /*
    Exercises:
      1. Expand MyList to use case classes and case objects
  */
end CaseClasses