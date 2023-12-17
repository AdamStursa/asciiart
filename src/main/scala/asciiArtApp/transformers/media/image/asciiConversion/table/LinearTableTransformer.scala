package asciiArtApp.transformers.media.image.asciiConversion.table

import asciiArtApp.models.media.pixel.GrayscalePixel
import asciiArtApp.models.table.asciiTransform.AsciiTransformTable

class LinearTableTransformer(characters: Array[Char] = Array('$', '@', 'B', '%', '8', '&', 'W', 'M', '#', '*', 'o', 'a', 'h', 'k', 'b', 'd', 'p', 'q', 'w', 'm', 'Z', 'O', '0', 'Q', 'L', 'C', 'J', 'U', 'Y', 'X', 'z', 'c', 'v', 'u', 'n', 'x', 'r', 'j', 'f', 't', '/', '\\', '|', '(', ')', '1', '{', '}', '[', ']', '?', '-', '_', '+', '~', '<', '>', 'i', '!', 'l', 'I', ';', ':', ',', '"', '^', '`', '\'', '.', ' ')) extends TableTransformer{

  override val name: String = LinearTableTransformer.name
  override val transformTable: AsciiTransformTable = new AsciiTransformTable()
  private val grayscalePerCharacter: Int = GrayscalePixel.MAX_VALUE/characters.length
  private var lastGrayscaleValue: Int = 0

  characters.foreach(char => {
    transformTable.addTransformPair(lastGrayscaleValue, char)
    lastGrayscaleValue += grayscalePerCharacter
  })

}

object LinearTableTransformer {
  val name: String = "Linear"
}
