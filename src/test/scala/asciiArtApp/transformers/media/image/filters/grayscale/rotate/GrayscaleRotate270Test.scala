package asciiArtApp.transformers.media.image.filters.grayscale.rotate

import asciiArtApp.models.media.image.GrayscaleImage
import helpers.ImageComparator
import org.scalatest.FunSuite

class GrayscaleRotate270Test extends FunSuite with ImageComparator {

  val rotate = new GrayscaleImageRotate270

  test("evenSize") {
    val image = new GrayscaleImage(2, 2)
    image.setAllPixels(Array(Array(42, 255), Array(142, 169)))
    val rotated = rotate.transform(image)

    val rotatedRef = new GrayscaleImage(2, 2)
    rotatedRef.setAllPixels(Array(Array(255, 169), Array(42, 142)))

    assertImagesAreSame(rotated, rotatedRef)
  }

  test("oddSize") {
    val image = new GrayscaleImage(3, 3)
    image.setAllPixels(
      Array(Array(1, 42, 255), Array(11, 142, 169), Array(21, 242, 80)))
    val rotated = rotate.transform(image)

    val rotatedRef = new GrayscaleImage(3, 3)
    rotatedRef.setAllPixels(
      Array(Array(255, 169, 80), Array(42, 142, 242), Array(1, 11, 21)))

    assertImagesAreSame(rotated, rotatedRef)
  }

  test("biggerWidthThanHeight") {
    val image = new GrayscaleImage(3, 2)
    image.setAllPixels(Array(Array(1, 42, 255), Array(11, 142, 169)))
    val rotated = rotate.transform(image)

    val rotatedRef = new GrayscaleImage(2, 3)
    rotatedRef.setAllPixels(
      Array(Array(255, 169), Array(42, 142), Array(1, 11)))

    assertImagesAreSame(rotated, rotatedRef)
  }

  test("biggerHeightThanWidth") {
    val image = new GrayscaleImage(2, 3)
    image.setAllPixels(Array(Array(42, 255), Array(142, 169), Array(242, 80)))
    val rotated = rotate.transform(image)

    val rotatedRef = new GrayscaleImage(3, 2)
    rotatedRef.setAllPixels(Array(Array(255, 169, 80), Array(42, 142, 242)))

    assertImagesAreSame(rotated, rotatedRef)
  }
}
