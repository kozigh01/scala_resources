import scala.math.*

case class Rational(num: Int, denom: Int)

given Ordering[Rational] with
  def compare(q: Rational, r: Rational): Int =
    q.num * r.denom - r.num * q.denom

def sort[A](xs: List[A])(ord: Ordering[A]): List[A] =
  def merge(xs: List[A], ys: List[A]): List[A] =
    (xs, ys) match
      case (left, Nil) => left
      case (Nil, right) => right
      case (x :: xsTail, y :: ysTail) =>
        if ord.lt(x, y) then    // a generic comparison
          x :: merge(xsTail, ys)
        else
          y :: merge(xs, ysTail)
  end merge

  val n = xs.length / 2
  if n == 0 then
    xs
  else
    val (left, right) = xs.splitAt(n)
    merge(sort(left)(ord), sort(right)(ord))
end sort

val rationals = List(Rational(6,2), Rational(7,2), Rational(5,2), Rational(5,3))
sort(rationals)
// sort(rationals)(using Ordering[Rational].reverse)
