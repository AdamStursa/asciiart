package asciiArtApp.models.table

import asciiArtApp.models.table.asciiTransform.AsciiTransformTable
import org.scalatest.FunSuite

import scala.collection.immutable.SortedMap

class AsciiTransformTableTest extends FunSuite {
  test("getGrayscaleValue") {
    val table = new AsciiTransformTable()
    table.addTransformPair(0, '0')
    table.addTransformPair(120, '1')
    table.addTransformPair(240, '2')
    table.addTransformPair(255, '3')

    assert(
      table.rows == SortedMap(0 -> '0', 120 -> '1', 240 -> '2', 255 -> '3'))

    assert(table.getGrayscaleAsciiValue(0) == '0')
    assert(table.getGrayscaleAsciiValue(100) == '0')
    assert(table.getGrayscaleAsciiValue(120) == '1')
    assert(table.getGrayscaleAsciiValue(200) == '1')
    assert(table.getGrayscaleAsciiValue(240) == '2')
    assert(table.getGrayscaleAsciiValue(255) == '3')
  }

  test("getGrayscaleDefaultValue") {
    val table = new AsciiTransformTable()

    assert(table.getGrayscaleAsciiValue(0) == ' ')
    assert(table.getGrayscaleAsciiValue(255) == ' ')

    table.addTransformPair(255, '1')

    assert(table.getGrayscaleAsciiValue(0) == ' ')
    assert(table.getGrayscaleAsciiValue(255) == '1')
  }

  test("overrideValue") {
    val table = new AsciiTransformTable()
    table.addTransformPair(0, '0')
    table.addTransformPair(0, '1')

    assert(table.getGrayscaleAsciiValue(0) == '1')

  }
}
