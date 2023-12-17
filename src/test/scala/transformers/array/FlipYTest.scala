package transformers.array

import org.scalatest.FunSuite

class FlipYTest extends FunSuite {

  test("odd") {
    val array = FlipY(Array(Array(1, 2, 3), Array(4, 5, 6)))

    assert(!array.isEmpty)
    assert(array(0)(0) == 3)
    assert(array(0)(1) == 2)
    assert(array(0)(2) == 1)
    assert(array(1)(0) == 6)
    assert(array(1)(1) == 5)
    assert(array(1)(2) == 4)
  }

  test("even") {
    val array = FlipY(Array(Array(1, 2, 3, 4), Array(5, 6, 7, 8)))

    assert(!array.isEmpty)
    assert(array(0)(0) == 4)
    assert(array(0)(1) == 3)
    assert(array(0)(2) == 2)
    assert(array(0)(3) == 1)
    assert(array(1)(0) == 8)
    assert(array(1)(1) == 7)
    assert(array(1)(2) == 6)
    assert(array(1)(3) == 5)
  }

  test("mixed") {
    val array = FlipY(Array(Array(1, 2, 3, 4), Array(5, 6, 7)))

    assert(!array.isEmpty)
    assert(array(0)(0) == 4)
    assert(array(0)(1) == 3)
    assert(array(0)(2) == 2)
    assert(array(0)(3) == 1)
    assert(array(1)(0) == 7)
    assert(array(1)(1) == 6)
    assert(array(1)(2) == 5)
  }

  test("empty"){
    assert(FlipY[Int](Array()).isEmpty)
    val array = FlipY[Int](Array(Array()))
    assert(!array.isEmpty)
    assert(array(0).isEmpty)
  }
}
