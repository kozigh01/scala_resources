case class Rectangle(width: Int, height: Int):
  val area = width * height

val fasade = Rectangle(5, 3)
fasade.area
val smallRectangle = Rectangle(width = 3, height = 4)
val largeRectangle = smallRectangle.copy(width = smallRectangle.width * 2)
smallRectangle.area
largeRectangle.area


case class Square(sideLength: Int):
  val area = sideLength * 2
val square1 = Square(6)
square1.area
case class Circle(radius: Int):
  val area = 3.14159 * radius * radius
val circle1 = Circle(3)
circle1.area


sealed trait Shape
case class Rectangle2(width: Int, height: Int) extends Shape
case class Circle2(radius: Int) extends Shape
val someShape: Shape = Circle2(5)
val rectangle2 = Rectangle2(3, 4)
val someShape2: Shape = rectangle2
val someShapeArea = someShape match
  case Rectangle2(width, height) => width * height
  case Circle2(radius) => 3.14 * radius * radius
someShapeArea
val circleShape = someShape match
  case circle: Circle2 => s"This is a circle with radius ${circle.radius}"
  case _ => "This is not a Circle2"
circleShape


enum PrimaryColor:
  case Red, Blue, Green
PrimaryColor.values
def isProblematicColorForBlind(color: PrimaryColor): Boolean =
  color match
    case PrimaryColor.Red => true
    case PrimaryColor.Blue => false
    case PrimaryColor.Green => true
isProblematicColorForBlind(PrimaryColor.Red)
isProblematicColorForBlind(PrimaryColor.Blue)
isProblematicColorForBlind(PrimaryColor.valueOf("Green"))
// PrimaryColor is equivalent to:
sealed trait PrimaryColor2
object PrimaryColor2:
  case object Red extends PrimaryColor2
  case object Green extends PrimaryColor2
  case object Blue extends PrimaryColor2
  val values = Array(Red, Blue, Green)
PrimaryColor2.values
