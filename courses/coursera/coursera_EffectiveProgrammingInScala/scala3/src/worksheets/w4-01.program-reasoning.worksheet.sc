// the problem - windowSide evaluates each time it is asked for
def windowSide1 = util.Random.between(1,4)
def windowArea1 = windowSide1 * windowSide1  // windowSide evulates twice
println(s"windowSide: $windowSide1, window area: $windowArea1");

// one solution, use val for windowSide
val windowSide2 = util.Random.between(1,4)
def windowArea2 = windowSide2 * windowSide2  // windowSide evulates twice
println(s"windowSide: $windowSide2, window area: $windowArea2");


// what does b + b print?
val b = 
  println("this happened")
  1

b + b  // doesn't print anything, since the println was done during the evaluation of b


// "pure" random number generator (Linear Congruential Generator)
class Generator(previous: Int):
  def nextInt(): (Int, Generator) =
    val result = previous * 22_695_477 + 1
    (result, Generator(result))

  def between(x: Int, y: Int): (Int, Generator) =
      val min = math.min(x, y)
      val delta = math.abs(x - y)
      val (randomValue, nextGenerator) = nextInt()
      ((math.abs(randomValue) % delta) + min, nextGenerator)

end Generator

object Generator:
  def init: Generator = Generator(42)

val gen1 = Generator.init
var (r1, gen2) = gen1.nextInt()
println(r1)
var (r2, _) = gen1.nextInt()
println(r2)  //calling nextInt on same generator gives same random number
var (r3, gen3) = gen2.nextInt()
println(r3)

val gen1_2 = Generator.init
val (r1_2, gen2_2) = gen1_2.between(1, 4)
val windowArea1_2 = r1_2 + r1_2
println(windowArea1_2)
val (r2_2, gen3_2) = gen2_2.between(5, 10)
val windowArea2_2 = r2_2 + r2_2
println(s"side: $r2_2, area: $windowArea2_2")


// "side-effect" random number generator
object Generator2:
  var previous: Int = 42

  def nextInt(): Int = 
    val result = previous + 22_695_477 + 1
    previous = result
    result

  def between(x: Int, y: Int): Int =
    val min = math.min(x, y)
    val delta = math.abs(x - y)
    math.abs(nextInt() % delta) + min

end Generator

Generator2.nextInt()
Generator2.nextInt()
Generator2.nextInt()
Generator2.between(1, 5)
Generator2.between(11, 55)



