package asciiArtApp.transformers.media.image.filters.grayscale.specific.rotate

import asciiArtApp.models.media.image.GrayscaleImage
import asciiArtApp.transformers.media.image.filters.grayscale.GrayscaleFilter
import transformers.array.Rotate270

class GrayscaleImageRotate270 extends GrayscaleFilter{
  override def transform(toRotate : GrayscaleImage): GrayscaleImage = {
    val rotated = toRotate.createEmpty(toRotate.height, toRotate.width)
    val pixels = toRotate.getAllPixels
    val rotatedPixels = Rotate270(pixels)
    rotated.setAllPixels(rotatedPixels)
    rotated
  }
}
