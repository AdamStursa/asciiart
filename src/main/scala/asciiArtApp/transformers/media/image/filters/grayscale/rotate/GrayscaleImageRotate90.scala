package asciiArtApp.transformers.media.image.filters.grayscale.rotate

import asciiArtApp.models.media.image.GrayscaleImage
import asciiArtApp.transformers.media.image.filters.grayscale.GrayscaleFilter
import transformers.array.Rotate90

class GrayscaleImageRotate90 extends GrayscaleFilter {
  override def transform(toRotate: GrayscaleImage): GrayscaleImage = {
    val rotated = toRotate.createEmpty(toRotate.height, toRotate.width)
    val pixels = toRotate.getAllPixels
    val rotatedPixels = Rotate90(pixels)
    rotated.setAllPixels(rotatedPixels)
    rotated
  }
}
