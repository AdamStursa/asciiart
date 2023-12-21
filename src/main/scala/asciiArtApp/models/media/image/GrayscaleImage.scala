package asciiArtApp.models.media.image

import asciiArtApp.models.media.pixel.GrayscalePixel

class GrayscaleImage(override val width: Int, override val height: Int)
    extends Image[GrayscalePixel](width, height) {
  override def createEmpty(width: Int, height: Int): GrayscaleImage =
    new GrayscaleImage(width, height)
}
