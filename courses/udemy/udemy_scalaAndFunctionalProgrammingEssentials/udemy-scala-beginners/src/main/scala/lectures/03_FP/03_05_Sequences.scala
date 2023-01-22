package lectures.`03_FP`

import java.util.Random

// collection hierarchy diagram: 
//    https://docs.scala-lang.org/scala3/book/collections-classes.html

object Sequences:
  // Seq - sequences
  def seqExaples() = 
    val seq1 = Seq(1,2,3,4)  // inhfrred as Seq[Int]
    println(s"seq1: $seq1") // Seq() actually defaults to a List() instanciation
    println(s"seq1.reverse: ${seq1.reverse}")
    println(s"seq1(2): ${seq1(2)}")  // get value at index 2
    println(s"seq1 ++ Seq(5,6,7): ${seq1 ++ Seq(5,6,7)}") // concatenation
    println(s"Seq(3,5,2,7,1,2).sorted: ${Seq(3,5,2,7,1,2).sorted}")

  def rangeExamples =
    val rng1 = 1 to 10    // inclusive
    rng1.foreach(x => print(x + " "))
    println()
    println(s"rng1: $rng1")
    println(s"rng1.toList: ${rng1.toList}")
    
    val rng2 = 1 until 10   // exclusive for ending value
    println(s"rng2: ${rng2.toList}")

  def listExamples = 
    val list1 = List(1,2,3)

    val list2 = 0 :: list1  // prepend a value to a list: syntactic sugar for ::.apply(0, list1)
    val list2_1 = ::(0, list1)
    val list2_2 = ::.apply(0, list1)
    println(s"list2: $list2")
    println(s"list2_1: $list2_1")
    println(s"list2_2: $list2_2")

    val list3 = 0 +: list1  // alternate prepending operator
    println(s"list3: $list3")
    val list3_1 = list3 :+ 4  // append to end of list
    println(s"list3_1: $list3_1")
    val list3_2 = -1 +: list1 :+ 42
    println(s"list3_2: $list3_2")

    val list4 = List.fill(5)("apple")
    println(s"list4: $list4")
    val listOf4 = List.fill(4)
    println(s"listOf4(42): ${listOf4(42)}")

    val list4Ooutput = list4.mkString("-|-")
    println(s"list4Output: $list4Ooutput")

  def ArrayExamples =
    //  equivalent to simeple Java arrays
    //    - can bee manually created with predefined lengths
    //    - are mutable
    //    - are interoperble with Java T[] arrays
    //    - indexing is fast
    val array1 = Array(1,2,4,5)
    val arraySize3 = Array.ofDim[Int](3)  // array with 3 spots, each initialized to default values (for primitive types) or null for reference tpes
    arraySize3.foreach(print)
    println
    println(arraySize3.mkString(" "))
    arraySize3(1) = 42  // syntactic sugar for: arraySize3.update(1, 42)
    println(s"arraySize3.mkString(\" \"): ${arraySize3.mkString(" ")}")

    // arrays and seq (note: Array is not in the Seq hierarchy)
    val arraySeq1: Seq[Int] = arraySize3
    println(s"arraySeq1: $arraySeq1")
    println(s"arraySeq1.mkString(\" \"): ${arraySeq1.mkString(" ")}")

  def vectorExamples =
    //  effectively constant time indexed read and write: O(log32(n))
    //  fast element addition: append/prepend
    //  implemented as a fixed-branched trie (branch factor 32)
    //  good performance for large sizes
    val vector1 = Vector.empty
    val vector2 = vector1 :+ 1 :+ 2 :+ 3   // Vector(1,2,3)
    val vector3 = vector2 updated (1, 42)
    println(s"vector2: $vector2")
    println(s"vector3: $vector3")
    
    val vector4 = Vector(1,2,3,4)
    println(s"vector4: $vector4")

    // collection performance
    val maxRuns = 1000
    val maxCapacity = 1000000
    def getWriteTime(collection: Seq[Int]) = 
      val r = Random()
      val times =
        for
          it <- 1 to maxRuns
        yield {
          val startTime = System.nanoTime()
          collection.updated(r.nextInt(maxCapacity), 42)
          System.nanoTime() - startTime
        }

      times.sum * 1.0 / maxRuns

    val listTime = getWriteTime((1 to maxCapacity).toList)
    val vectorTime = getWriteTime((1 to maxCapacity).toVector)
    // val listTime = getWriteTime(List.fill(maxCapacity)(0))
    // val vectorTime = getWriteTime(Vector.fill(maxCapacity)(0))
    println(s"listTime, vectorTime, vectorTime/listTime %: $listTime, $vectorTime, ${"%1.5f".format(vectorTime/listTime * 100)}%")

end Sequences


@main def sequencesMain() =
  println("----------- Sequences ----------\n")

  // Sequences.seqExaples()
  // Sequences.rangeExamples
  // Sequences.listExamples
  // Sequences.ArrayExamples
  Sequences.vectorExamples
