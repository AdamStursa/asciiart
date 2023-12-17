package asciiArtApp.transformers.media.image.rgbToGrayscale

import asciiArtApp.models.media.image.{GrayscaleImage, RGBImage}
import asciiArtApp.transformers.media.image.ImageToImageTransformer

private class RGBToGrayscaleTransformer extends ImageToImageTransformer[RGBImage, GrayscaleImage] {

  override def transform(original: RGBImage): GrayscaleImage = {
    val converted = GrayscaleImage(original.width, original.height)

    val grayscaleValues = original.getAllPixels.map(_.map(i => i.grayscaleValue))

    for (i <- 0 until original.height)
      for (j <- 0 until original.width)
        converted.setPixel(i, j, grayscaleValues(i)(j))

    converted
  }
}

object RGBToGrayscaleTransformer {
  def apply(rgbImage: RGBImage): GrayscaleImage = {
    new RGBToGrayscaleTransformer().transform(rgbImage)
  }
}
