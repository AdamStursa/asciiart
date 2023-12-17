package asciiArtApp.models.media.image

import asciiArtApp.models.media.pixel.GrayscalePixel

case class GrayscaleImage (override val width: Int, override val height: Int) extends Image[GrayscalePixel](width, height) {
  override def createEmpty(width: Int, height: Int): GrayscaleImage = GrayscaleImage(width, height)
}

