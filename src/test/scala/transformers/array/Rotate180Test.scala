package transformers.array

import org.scalatest.FunSuite

class Rotate180Test extends FunSuite {
  test("dimensionsSame") {
    val array = Rotate180[Int](Array.fill[Int](2, 3)(1))

    assert(!array.isEmpty)
    assert(array.length == 2)
    assert(array(0).length == 3)
    assert(array(1).length == 3)
  }

  test("valuesRotate180") {
    val array = Rotate180[Int](Array(Array(1, 2, 3), Array(4, 5, 6)))

    assert(!array.isEmpty)
    assert(array(0)(0) == 6)
    assert(array(0)(1) == 5)
    assert(array(0)(2) == 4)
    assert(array(1)(0) == 3)
    assert(array(1)(1) == 2)
    assert(array(1)(2) == 1)
  }

  test( "badArrayInput") {
    assertThrows[IllegalArgumentException](Rotate180[Int](Array()))
    assertThrows[IllegalArgumentException](Rotate180[Int](Array(Array())))
    assertThrows[IllegalArgumentException](Rotate180[Int](Array(Array(1, 2), Array(1, 2, 3))))
  }
}
