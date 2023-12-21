package asciiArtApp.transformers.media.image.filters.grayscale.flip

import asciiArtApp.models.media.image.GrayscaleImage
import helpers.ImageComparator
import org.scalatest.FunSuite

class GrayscaleImageFlipXTest extends FunSuite with ImageComparator {

  val flipX = new GrayscaleImageFlipX

  test("evenSize") {
    val image = new GrayscaleImage(2, 2)
    image.setAllPixels(Array(Array(42, 255), Array(142, 169)))
    val flipped = flipX.transform(image)

    val flippedRef = new GrayscaleImage(2, 2)
    flippedRef.setAllPixels(Array(Array(142, 169), Array(42, 255)))

    assertImagesAreSame(flipped, flippedRef)
  }

  test("oddSize") {
    val image = new GrayscaleImage(3, 3)
    image.setAllPixels(
      Array(Array(1, 42, 255), Array(11, 142, 169), Array(21, 242, 80)))
    val flipped = flipX.transform(image)

    val flippedRef = new GrayscaleImage(3, 3)
    flippedRef.setAllPixels(
      Array(Array(21, 242, 80), Array(11, 142, 169), Array(1, 42, 255)))

    assertImagesAreSame(flipped, flippedRef)
  }
}
