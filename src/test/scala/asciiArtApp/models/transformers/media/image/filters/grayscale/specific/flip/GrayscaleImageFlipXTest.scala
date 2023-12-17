package asciiArtApp.models.transformers.media.image.filters.grayscale.specific.flip

import asciiArtApp.models.media.image.GrayscaleImage
import asciiArtApp.transformers.media.image.filters.grayscale.specific.flip.GrayscaleImageFlipX
import org.scalatest.FunSuite

class GrayscaleImageFlipXTest extends FunSuite {

  test("grayscaleImage") {
    val image = GrayscaleImage(2, 2)
    image.setAllPixels(Array(Array(42, 255),
                             Array(142, 169)))
    val flipped = new GrayscaleImageFlipX().transform(image)
    val flippedRef = GrayscaleImage(2, 2)
    flippedRef.setAllPixels(Array(Array(142, 169),
                               Array(42, 255)))

    for (x <- 0 until 2)
      for (y <- 0 until 2)
        assert(flipped.getPixel(x, y) == flippedRef.getPixel(x, y))
  }
}
