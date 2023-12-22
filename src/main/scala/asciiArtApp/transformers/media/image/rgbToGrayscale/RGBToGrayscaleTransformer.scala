package asciiArtApp.transformers.media.image.rgbToGrayscale

import asciiArtApp.models.media.image.{GrayscaleImage, RGBImage}
import asciiArtApp.models.media.pixel.RGBPixel
import asciiArtApp.transformers.media.image.ImageToImageTransformer

class RGBToGrayscaleTransformer
    extends ImageToImageTransformer[RGBImage, GrayscaleImage] {

  override def transform(original: RGBImage): GrayscaleImage = {
    val converted = new GrayscaleImage(original.width, original.height)

    val grayscaleValues =
      original.getAllPixels.map(_.map(i => grayscaleValue(i)))

    for (i <- 0 until original.height)
      for (j <- 0 until original.width)
        converted.setPixel(i, j, grayscaleValues(i)(j))

    converted
  }

  private def grayscaleValue(pixel: RGBPixel): Int =
    Math.round(
      (0.3f * pixel.red) + (0.59f * pixel.green) + (0.11f * pixel.blue))
}
