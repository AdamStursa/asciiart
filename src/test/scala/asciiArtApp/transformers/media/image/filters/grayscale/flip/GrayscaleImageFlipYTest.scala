package asciiArtApp.transformers.media.image.filters.grayscale.flip

import asciiArtApp.models.media.image.GrayscaleImage
import org.scalatest.FunSuite

class GrayscaleImageFlipYTest extends FunSuite {

  val flipY = new GrayscaleImageFlipY

  test("evenSize") {
    val image = new GrayscaleImage(2, 2)
    image.setAllPixels(Array(Array(42, 255), Array(142, 169)))
    val flipped = flipY.transform(image)

    val flippedRef = new GrayscaleImage(2, 2)
    flippedRef.setAllPixels(Array(Array(255, 42), Array(169, 142)))

    for (x <- 0 until 2)
      for (y <- 0 until 2)
        assert(flipped.getPixel(x, y) == flippedRef.getPixel(x, y))
  }

  test("oddSize") {
    val image = new GrayscaleImage(3, 3)
    image.setAllPixels(
      Array(Array(1, 42, 255), Array(11, 142, 169), Array(21, 242, 80)))
    val flipped = flipY.transform(image)

    val flippedRef = new GrayscaleImage(3, 3)
    flippedRef.setAllPixels(
      Array(Array(255, 42, 1), Array(169, 142, 11), Array(80, 242, 21)))

    for (x <- 0 until 3)
      for (y <- 0 until 3)
        assert(flipped.getPixel(x, y) == flippedRef.getPixel(x, y))
  }
}
