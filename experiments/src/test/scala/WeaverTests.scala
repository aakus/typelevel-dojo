import weaver.*
import cats.effect.IO
import weaver.scalacheck.Checkers
import org.scalacheck.Gen

object WeaverTests extends SimpleIOSuite with Checkers:

  test("io test") {
    IO(1).map(i => expect(i == 1))
  }

  pureTest("pure test") {
    expect(2 + 2 == 4)
  }

  test("prop test") {
    forall(Gen.posNum[Int]) { a =>
      expect(a > 0)
    }
  }
