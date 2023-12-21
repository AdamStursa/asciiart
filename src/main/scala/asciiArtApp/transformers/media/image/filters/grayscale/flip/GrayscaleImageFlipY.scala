package asciiArtApp.transformers.media.image.filters.grayscale.flip

import asciiArtApp.models.media.image.GrayscaleImage
import asciiArtApp.transformers.media.image.filters.grayscale.GrayscaleFilter
import transformers.array.FlipY

class GrayscaleImageFlipY extends GrayscaleFilter {
  override def transform(toFlip: GrayscaleImage): GrayscaleImage = {
    val flipped = toFlip.createEmpty(toFlip.width, toFlip.height)
    val pixels = toFlip.getAllPixels
    val rotatedPixels = FlipY(pixels)
    flipped.setAllPixels(rotatedPixels)
    flipped
  }
}
