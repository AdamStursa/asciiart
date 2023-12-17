package asciiArtApp.transformers.media.image.filters.grayscale.specific.rotate

import asciiArtApp.models.media.image.GrayscaleImage
import asciiArtApp.transformers.media.image.filters.grayscale.GrayscaleFilter
import transformers.array.Rotate180

class GrayscaleImageRotate180 extends GrayscaleFilter{
  override def transform(toRotate : GrayscaleImage): GrayscaleImage = {
    val rotated = toRotate.createEmpty(toRotate.width, toRotate.height)
    val pixels = toRotate.getAllPixels
    val rotatedPixels = Rotate180(pixels)
    rotated.setAllPixels(rotatedPixels)
    rotated
  }
}
