package hellosbt

import munit.FunSuite

class HelloSbtSuite extends FunSuite:
  test("try the impossible") {
    assert(1 == 0)
  }
  test("do the right thing") {
    assert(1 == 1)
  }