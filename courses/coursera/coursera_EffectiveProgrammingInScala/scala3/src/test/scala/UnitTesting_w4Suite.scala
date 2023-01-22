package unitTesting_w4

import org.scalacheck.Prop.forAll
import org.scalacheck.Gen

class UnitTesting_w4Suite extends munit.FunSuite:
  test("add") {
    val obtained = add(1,1)
    val expected = 2
    assertEquals(obtained, expected)
  }

  test("fibonacci") {
    assertEquals(fibonacci(0), 0)
    assertEquals(fibonacci(1), 1)
    assertEquals(fibonacci(2), 1)
    assertEquals(fibonacci(3), 2)
    // assertEquals(fibonacci(4), 1)
  }

end UnitTesting_w4Suite


// property testing using munit-scalachck
class ProgramProperties extends munit.ScalaCheckSuite:
  val fibonacciDomain: Gen[Int] = Gen.choose(1, 20)
  // val fibonacciDomain: Gen[Int] = Gen.choose(3, Int.MaxValue)

  property("fibonacci(n) == fibonacci(n-1) + fibonacci(n-2)") {
    forAll(fibonacciDomain.suchThat(n => n >= 3)) {
      (n: Int) => fibonacci(n) == fibonacci(n-1) + fibonacci(n-2)
    }
  }

  property("fibonacci numbers are positiv") {
    forAll(fibonacciDomain) {
      (n: Int) => fibonacci(n) >= 0
    }
  }

end ProgramProperties


// Integration testing

// Setting up a resource for lifetime of single test
// class HttpServerSuite extends munit.FunSuite:
//   val withHttpServer = FunFixture[HttpServer](
//     setup = test => {
//       val httpServer = HttpServer()
//       httpServer.start(8088)
//       httpServer
//     },
//     teardown = httpSerer => httpSerer.stop()
//   )

//   // use http server in one test
//   withHttpServer.test("server is running") {
//     httpSerer => // Perform HTTP request here
//   }

// // Setting up a resource for all tests
// class HttpServerSuite2 extends munit.FunSuite:
//   val httpServer = HttpServer()

//   override def beforeAll(): Unit = httpServer.start(8888)
//   override def afterAll(): Unit = httpServer.stop()

//   // use http server in one test
//   test("server is running") {
//     // use Http request here
//   }