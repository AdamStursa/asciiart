package asciiArtApp.models.media.image

import asciiArtApp.models.media.pixel.RGBPixel

case class RGBImage (override val width: Int, override val height: Int) extends Image[RGBPixel](width, height) {
  override def createEmpty(width: Int, height: Int): RGBImage = RGBImage(width, height)
}
