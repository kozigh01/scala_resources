1 + 1
"Hello, world".length()
1 > 0
1 == 0
1.max(0)
-5.abs
"Hello, " + "world!"
"#" * 3
"Alice".toUpperCase()
true && true
true && false
true.&&(false)
1 + 2.0

val fasade = 5 * 3
val door = 1 * 2
fasade - door

def wallPaint(fasadeArea: Double, windowArea: Double, doorArea: Double): Double =
  fasadeArea - 2 * windowArea - doorArea

wallPaint(5 * 3, 1 * 1, 3 * 1)

def marathonDuraton(speed: Double): Double =
  val distance = 42.195
  val duration = distance / speed
  duration * 60

val aliceDuration = marathonDuraton(12)
val bobDuration = marathonDuraton(14)

def showPaintPrice(area: Double, price: Double): String =
  val totalPrice = area * price
  if price > 100 then
    "this is too expensive"
  else if price < 10 then
    "this is very cheap"
  else
    totalPrice.toString

val expensivePaint = showPaintPrice(50, 3.5)
val okayPaint = showPaintPrice(50, 1.75)
val cheapPaint = showPaintPrice(50, .75)


