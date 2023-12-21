package helpers

import asciiArtApp.models.media.image.RGBImage
import asciiArtApp.models.media.pixel.RGBPixel

import scala.util.Random

class CustomTestRandom extends Random {

  var accumulator: Int = 1
  override def between(minInclusive: Int, maxExclusive: Int): Int = {
    accumulator += 1
    accumulator
  }
}

object CustomTestRandom {
  def imageReference: RGBImage = {
    val img = new RGBImage(2, 3)
    img.setAllPixels(
      Array(
        Array(RGBPixel(4, 5, 6), RGBPixel(7, 8, 9)),
        Array(RGBPixel(10, 11, 12), RGBPixel(13, 14, 15)),
        Array(RGBPixel(16, 17, 18), RGBPixel(19, 20, 21))))
    img
  }
}
