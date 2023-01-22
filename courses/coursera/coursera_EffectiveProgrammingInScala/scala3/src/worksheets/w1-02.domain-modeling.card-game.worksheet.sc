/**
 * Business Logic
 **/
// Set card game
enum CardShape:
  case Diamond, Squiggle, Oval
enum CardNumber:
  case One, Two, Three
enum CardShading:
  case Solid, Striped, Open
enum CardColor:
  case Red, Green, Purple

case class Card(shape: CardShape, number: CardNumber, shading: CardShading, color: CardColor)
case class CardSet(card1: Card, card2: Card, card3: Card)

val validSet = CardSet(
  Card(CardShape.Diamond, CardNumber.One, CardShading.Striped, CardColor.Purple),
  Card(CardShape.Squiggle, CardNumber.Two, CardShading.Open, CardColor.Red),
  Card(CardShape.Oval, CardNumber.Three, CardShading.Solid, CardColor.Green)
)

val invalidSet = CardSet(
  Card(CardShape.Diamond, CardNumber.One, CardShading.Striped, CardColor.Purple),
  Card(CardShape.Squiggle, CardNumber.Two, CardShading.Open, CardColor.Red),
  Card(CardShape.Oval, CardNumber.One, CardShading.Solid, CardColor.Green)
)

def isValidSet(cs: CardSet): Boolean =
  isValidShapes(cs)
  && isValidNumbers(cs)
  && isValiidShadings(cs)
  && isValidColors(cs)

def isValidShapes(cs: CardSet): Boolean =
  val allSame = cs.card1.shape == cs.card2.shape && cs.card2.shape == cs.card3.shape
  val allDifferent = 
    cs.card1.shape != cs.card2.shape
    && cs.card2.shape != cs.card3.shape
    && cs.card1.shape != cs.card3.shape
  allSame || allDifferent

def isValidNumbers(cs: CardSet): Boolean =
  val allSame = cs.card1.number == cs.card2.number && cs.card2.number == cs.card3.number
  val allDifferent = 
    cs.card1.number != cs.card2.number
    && cs.card2.number != cs.card3.number
    && cs.card1.number != cs.card3.number
  allSame || allDifferent

def isValiidShadings(cs: CardSet): Boolean =
  val allSame = cs.card1.shading == cs.card2.shading && cs.card2.shading == cs.card3.shading
  val allDifferent = 
    cs.card1.shading != cs.card2.shading
    && cs.card2.shading != cs.card3.shading
    && cs.card1.shading != cs.card3.shading
  allSame || allDifferent
  allSame || allDifferent

def isValidColors(cs: CardSet): Boolean =
  val allSame = cs.card1.color == cs.card2.color && cs.card2.color == cs.card3.color
  val allDifferent = 
    cs.card1.color != cs.card2.color
    && cs.card2.color != cs.card3.color
    && cs.card1.color != cs.card3.color
  allSame || allDifferent
  allSame || allDifferent

isValidSet(validSet)
isValidSet(invalidSet)