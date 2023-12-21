package asciiArtApp.models.media.pixel

import asciiArtApp.models.media.pixel.GrayscalePixel.{MAX_VALUE, MIN_VALUE}

import scala.language.implicitConversions

case class GrayscalePixel(value: Int) extends Pixel {
  require(
    value >= MIN_VALUE && value <= MAX_VALUE,
    f"Grayscale  pixel value must be between $MIN_VALUE and $MAX_VALUE")
}

object GrayscalePixel {
  val MIN_VALUE = 0
  val MAX_VALUE = 255

  implicit def covertFromInt(value: Int): GrayscalePixel =
    new GrayscalePixel(value)

  implicit def covertToInt(grayscalePixel: GrayscalePixel): Int =
    grayscalePixel.value
}
