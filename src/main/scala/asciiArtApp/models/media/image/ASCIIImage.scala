package asciiArtApp.models.media.image

import asciiArtApp.models.media.pixel.CharacterPixel

class ASCIIImage(override val width: Int, override val height: Int)
    extends Image[CharacterPixel](width, height) {
  override def createEmpty(width: Int, height: Int): ASCIIImage =
    new ASCIIImage(width, height)
}
