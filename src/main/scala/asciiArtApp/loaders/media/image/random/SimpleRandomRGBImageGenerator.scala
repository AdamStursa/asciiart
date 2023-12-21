package asciiArtApp.loaders.media.image.random

import asciiArtApp.loaders.media.image.ImageLoader
import asciiArtApp.models.media.image.RGBImage
import asciiArtApp.models.media.pixel.RGBPixel

import scala.util.Random

class SimpleRandomRGBImageGenerator(val rand: Random)
    extends ImageLoader[RGBImage] {
  private val MIN_SIZE = 8
  private val MAX_SIZE = 64
  private val MIN_RGB = 0
  private val MAX_RGB = 255

  override def load(): RGBImage = {
    val width = rand.between(MIN_SIZE, MAX_SIZE)
    val height = rand.between(MIN_SIZE, MAX_SIZE)

    val rgbImage = new RGBImage(width, height)

    for (i <- 0 until height)
      for (j <- 0 until width)
        rgbImage.setPixel(
          i,
          j,
          new RGBPixel(
            rand.between(MIN_RGB, MAX_RGB),
            rand.between(MIN_RGB, MAX_RGB),
            rand.between(MIN_RGB, MAX_RGB)))

    rgbImage
  }
}
