package com.mkozi

import org.scalatest.funsuite.AnyFunSuite

class SimpleTest extends AnyFunSuite:
  test("simplest test possible") {
    assert("Scala".toLowerCase() == "scala")
  }