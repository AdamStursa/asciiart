package asciiArtApp.transformers.media.image.filters.grayscale.invert

import asciiArtApp.models.media.image.GrayscaleImage
import asciiArtApp.models.media.pixel.GrayscalePixel
import asciiArtApp.transformers.media.image.filters.grayscale.GrayscaleFilter

class GrayscaleImageInvert extends GrayscaleFilter {

  override def transform(toInvert: GrayscaleImage): GrayscaleImage = {
    val invertedPixels =
      toInvert.getAllPixels.map(_.map(p => GrayscalePixel(255 - p.value)))
    val inverted = new GrayscaleImage(toInvert.width, toInvert.height)
    inverted.setAllPixels(invertedPixels)
    inverted
  }
}
