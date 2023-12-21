package asciiArtApp.transformers.media.image.asciiConversion.table

import asciiArtApp.models.media.pixel.GrayscalePixel
import asciiArtApp.models.table.asciiTransform.AsciiTransformTable

class LinearTableTransformer(
  characters: Array[Char] = Array('$', '@', 'B', '%', '8', '&', 'W', 'M', '#',
    '*', 'o', 'a', 'h', 'k', 'b', 'd', 'p', 'q', 'w', 'm', 'Z', 'O', '0', 'Q',
    'L', 'C', 'J', 'U', 'Y', 'X', 'z', 'c', 'v', 'u', 'n', 'x', 'r', 'j', 'f',
    't', '/', '\\', '|', '(', ')', '1', '{', '}', '[', ']', '?', '-', '_', '+',
    '~', '<', '>', 'i', '!', 'l', 'I', ';', ':', ',', '"', '^', '`', '\'', '.',
    ' '))
    extends TableTransformer {
  require(characters.nonEmpty, "Character array cannot be empty")
  require(
    characters.length <= GrayscalePixel.MAX_VALUE,
    f"Character array cannot be bigger than ${GrayscalePixel.MAX_VALUE}")

  override val name: String = LinearTableTransformer.name
  override val transformTable: AsciiTransformTable = new AsciiTransformTable()
  private val grayscalePerCharacter
    : Float = GrayscalePixel.MAX_VALUE.toFloat / characters.length
  private var lastGrayscaleValue: Float = 0f

  characters.foreach(char => {
    transformTable.addTransformPair(lastGrayscaleValue.toInt, char)
    lastGrayscaleValue += grayscalePerCharacter
  })

}

object LinearTableTransformer {
  val name: String = "Linear"
}
