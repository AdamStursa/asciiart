package asciiArtApp.models.media.pixel

import asciiArtApp.models.media.pixel.RGBPixel.{MAX_VALUE, MIN_VALUE}

case class RGBPixel(val red: Int, val green: Int, val blue: Int) extends Pixel {
  require(
    red >= MIN_VALUE && red <= MAX_VALUE && green >= MIN_VALUE
    && green <= MAX_VALUE && blue >= MIN_VALUE && blue <= MAX_VALUE,
    f"Each color value must be number between $MIN_VALUE and $MAX_VALUE"
  )
  def grayscaleValue: Int = ((0.3 * red) + (0.59 * green) + (0.11 * blue)).toInt
}

object RGBPixel {
  val MAX_VALUE = 255
  val MIN_VALUE = 0
}
