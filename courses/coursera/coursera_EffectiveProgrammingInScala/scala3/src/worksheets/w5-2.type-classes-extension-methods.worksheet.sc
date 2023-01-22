case class Rational(num: Int, denom: Int)

val x: Rational = Rational(1, 2)
val y: Rational = Rational(3, 4)

extension (lhs: Rational)
  def < (rhs: Rational) =
    lhs.num * rhs.denom < rhs.num * lhs.denom

x < y
y < x


trait Ordering[A]:
  def compare(x: A, y: A): Int
  extension (lhs: A)
    def < (rhs: A): Boolean = compare(lhs, rhs) < 0

given Ordering[Rational] with
  def compare(q: Rational, r: Rational): Int =
    q.num * r.denom - r.num * q.denom

def lessThan[A: Ordering](x: A, y: A): Boolean =
  x < y

lessThan(x, y)
lessThan(y, x)