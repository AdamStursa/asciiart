package asciiArtApp.models.media.image

import asciiArtApp.models.media.Media

import scala.reflect.ClassTag

abstract class Image[T : ClassTag](val width: Int, val height: Int) extends Media {
  require(width > 0 && height > 0, "Width and height must be grater than 0")

  private val pixels: Array[Array[T]] = Array.ofDim[T](height, width)

  def setPixel(x: Int, y: Int, pixel: T): Unit = {
    require(x >= 0 && x < height && y >= 0 && y < width, f"Coordinates ($x, $y) are out of bounds")
    pixels(x)(y) = pixel
  }

  def getPixel(x: Int, y: Int): T = {
    pixels(x)(y).ensuring(p => p != null, f"Pixel value on coordinates ($x, $y) is not set")
  }

  def getAllPixels: Array[Array[T]] = pixels

  def setAllPixels(input: Array[Array[T]]): Unit = {
    require(input.length == height, "Input array must have same height as image")
    input foreach(inner => require(inner.length == width, "Input array must have same width as image"))

    for(x <- 0 until height)
      for(y <- 0 until width)
        setPixel(x, y, input(x)(y))
  }

  def createEmpty(width: Int, height: Int): Image[T]
}
