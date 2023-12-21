package asciiArtApp.models.table.asciiTransform

import asciiArtApp.models.table.Table

import scala.collection.mutable

class AsciiTransformTable extends Table[mutable.SortedMap[Int, Char]] {

  private val DEFAULT_WHITESPACE = ' '
  override val rows: mutable.SortedMap[Int, Char] = mutable.SortedMap()

  def getGrayscaleAsciiValue(grayscale: Int): Char =
    rows.keys.toArray.sorted.findLast(_ <= grayscale) match {
      case None      => DEFAULT_WHITESPACE
      case Some(key) => rows(key)
    }

  def addTransformPair(from: Int, char: Char): Unit =
    rows.addOne((from, char))
}
