package transformers.array

import org.scalatest.FunSuite

class Rotate270Test extends FunSuite {
  test("dimensionsSwap") {
    val array = Rotate270[Int](Array.fill[Int](2, 3)(1))

    assert(!array.isEmpty)
    assert(array.length == 3)
    assert(array(0).length == 2)
    assert(array(1).length == 2)
    assert(array(2).length == 2)
  }

  test("valuesRotate270") {
    val array = Rotate270[Int](Array(Array(1, 2, 3), Array(4, 5, 6)))

    assert(!array.isEmpty)
    assert(array(0)(0) == 3)
    assert(array(0)(1) == 6)
    assert(array(1)(0) == 2)
    assert(array(1)(1) == 5)
    assert(array(2)(0) == 1)
    assert(array(2)(1) == 4)
  }

  test("badArrayInput") {
    assertThrows[IllegalArgumentException](Rotate270[Int](Array()))
    assertThrows[IllegalArgumentException](Rotate270[Int](Array(Array())))
    assertThrows[IllegalArgumentException](
      Rotate270[Int](Array(Array(1, 2), Array(1, 2, 3))))
  }
}
