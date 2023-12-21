package asciiArtApp.models.media.image

import asciiArtApp.models.media.pixel.RGBPixel

class RGBImage(override val width: Int, override val height: Int)
    extends Image[RGBPixel](width, height) {
  override def createEmpty(width: Int, height: Int): RGBImage =
    new RGBImage(width, height)
}
