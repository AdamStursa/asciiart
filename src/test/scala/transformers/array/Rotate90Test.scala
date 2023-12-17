package transformers.array

import org.scalatest.FunSuite

class Rotate90Test extends FunSuite {
  test("dimensionsSwap") {
    val array = Rotate90[Int](Array.fill[Int](2, 3)(1))

    assert(!array.isEmpty)
    assert(array.length == 3)
    assert(array(0).length == 2)
    assert(array(1).length == 2)
    assert(array(2).length == 2)
  }
  
  test("valuesRotate90") {
    val array = Rotate90[Int](Array(Array(1, 2, 3), Array(4, 5, 6)))

    assert(!array.isEmpty)
    assert(array(0)(0) == 4)
    assert(array(0)(1) == 1)
    assert(array(1)(0) == 5)
    assert(array(1)(1) == 2)
    assert(array(2)(0) == 6)
    assert(array(2)(1) == 3)
  }

  test( "badArrayInput") {
    assertThrows[IllegalArgumentException](Rotate90[Int](Array()))
    assertThrows[IllegalArgumentException](Rotate90[Int](Array(Array())))
    assertThrows[IllegalArgumentException](Rotate90[Int](Array(Array(1, 2), Array(1, 2, 3))))
  }
}
