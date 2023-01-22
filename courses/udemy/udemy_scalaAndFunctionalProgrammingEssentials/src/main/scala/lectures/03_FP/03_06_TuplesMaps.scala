package lectures.`03_FP`

object TuplesMaps:
  def tuplesExamples = 
    // tuples = finite ordered "lists"
    val tuple1 = Tuple2(2, "Hello, Scala")
    val tuple2 = (false, "Hello, Scala again")
    println(s"tuple1, _1, _2: $tuple1 ${tuple1._1} ${tuple1._2}")
    println(s"tuple1.copy(_2 = \"goodbye java\"): ${tuple1.copy(_2 = "goodbye java")}")
    println(s"tuple1.swap: ${tuple1.swap}")
    println(s"tuple2: $tuple2")

  def mapExamples =
    // maps - associate keys to values
    val map1: Map[String, Int] = Map()
    val phonebook = Map(("Jim", 555), "Daniel" -> 789)

    println(s"phonebook: $phonebook")

    println(s"phonebook.contains(\"Jim\"): ${phonebook.contains("Jim")}")
    println(s"phonebook.contains(\"jim\"): ${phonebook.contains("jim")}")
    println(s"phonebook(\"Jim\"): ${phonebook("Jim")}")
    // println(s"phonebook(\"jim\"): ${phonebook("jim")}") // throws NoSuchElementException

    val phonebook2 = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
    println(s"phonebook2(\"mary\"): ${phonebook2("mary")}") // doesn't throw exception - has a default

    val mary = "Mary" -> 678
    val phonebook3 = phonebook + mary
    println(s"phonebook3: $phonebook3")
    println(s"phonebook3 map: ${phonebook3.map((k,v) => (k.toLowerCase, v + 2))}")
    println(s"phonebook3 filterKeys: ${phonebook3.view.filterKeys(_.startsWith("M")).toMap}")
    println(s"phonebook3 mapValues: ${phonebook3.view.mapValues(_ * 10).toMap}")
    println(s"phonebook3 mapValues: ${phonebook3.view.mapValues("0245-" + _).toMap}")

    // conversions
    println(s"phonebook3.toList: ${phonebook3.toList}")
    println(s"List((Jim,555), (Daniel,789), (Mary,678)).toMap: ${List(("Jim",555), ("Daniel",789), ("Mary",678)).toMap}")

    // group by
    val names = List("Bob", "James", "Angela", "Brody", "Marty", "Daniel", "Jim", "Bill")
    println(s"names groupBy: ${names.groupBy(name => name.charAt(0))}")

  def exercise1 =
    val map1 = Map("Jim" -> 555, "JIM" -> 666, "jiM" -> 777)
    println(s"map1 - keys to lower case: ${map1.map((k, v) => (k.toLowerCase(), v))}")

    val map2 = Map("JIM" -> 666, "jiM" -> 777, "Jim" -> 555)
    println(s"map2 - keys to lower case: ${map2.map((k, v) => (k.toLowerCase(), v))}")

    val map3 = Map("jiM" -> 777, "Jim" -> 555, "JIM" -> 666)
    println(s"map3 - keys to lower case: ${map3.map((k, v) => (k.toLowerCase(), v))}")

  def exercise2 =
    def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
      network + (person -> Set())
    def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
      network.map((k, v) => (
        k,
        if k == a then v + b
        else if k == b then v + a
        else v
      ))
    def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
      network.view.filterKeys(k => k != person).toMap
    def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
      network.map((k, v) => (
        k,
        if k == a then v.filter(v => v != b)
        else if k == b then v.filter(v => v != a)
        else v
      ))

    val network = Map("Jim" -> Set[String](), "Bob" -> Set[String](), "Kevin" -> Set[String]())
    println(s"network: $network")
    val network2 = add(network, "Ray")
    println(s"network after add: $network2")
    val network3 = friend(network2, "Kevin", "Ray")
    val network4 = friend(network3, "Kevin", "Jim")
    println(s"network after friend: $network4")
    println(s"network after remove 'Bob': ${remove(network4, "Bob")}")
    println(s"network unfriend 'Kevin' - 'Ray': ${unfriend(network4, "Kevin", "Ray")}")


end TuplesMaps


@main def tuplesAndMapsMain =
  // TuplesMaps.tuplesExamples
  // TuplesMaps.mapExamples
  // TuplesMaps.exercise1
  TuplesMaps.exercise2