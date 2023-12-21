package transformers.array

import org.scalatest.FunSuite

class FlipXTest extends FunSuite {

  test("odd") {
    val array = FlipX(Array(Array(1), Array(2), Array(3)))

    assert(!array.isEmpty)
    assert(array(0)(0) == 3)
    assert(array(1)(0) == 2)
    assert(array(2)(0) == 1)
  }

  test("even") {
    val array = FlipX(Array(Array(1, 2), Array(3, 4)))

    assert(!array.isEmpty)
    assert(array(0)(0) == 3)
    assert(array(0)(1) == 4)
    assert(array(1)(0) == 1)
    assert(array(1)(1) == 2)
  }

  test("mixed") {
    val array = FlipX(Array(Array(1, 2, 3), Array(4, 5)))

    assert(!array.isEmpty)
    assert(array(0).length == 2)
    assert(array(1).length == 3)
    assert(array(0)(0) == 4)
    assert(array(0)(1) == 5)
    assert(array(1)(0) == 1)
    assert(array(1)(1) == 2)
    assert(array(1)(2) == 3)
  }

  test("empty") {
    assert(FlipX[Int](Array()).isEmpty)
    val array = FlipX[Int](Array(Array()))
    assert(!array.isEmpty)
    assert(array(0).isEmpty)
  }
}
