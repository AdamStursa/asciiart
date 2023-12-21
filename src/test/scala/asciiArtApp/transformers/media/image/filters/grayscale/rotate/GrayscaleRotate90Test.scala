package asciiArtApp.transformers.media.image.filters.grayscale.rotate

import asciiArtApp.models.media.image.GrayscaleImage
import helpers.ImageComparator
import org.scalatest.FunSuite

class GrayscaleRotate90Test extends FunSuite with ImageComparator {

  val rotate = new GrayscaleImageRotate90

  test("evenSize") {
    val image = new GrayscaleImage(2, 2)
    image.setAllPixels(Array(Array(42, 255), Array(142, 169)))
    val rotated = rotate.transform(image)

    val rotatedRef = new GrayscaleImage(2, 2)
    rotatedRef.setAllPixels(Array(Array(142, 42), Array(169, 255)))

    assertImagesAreSame(rotated, rotatedRef)
  }

  test("oddSize") {
    val image = new GrayscaleImage(3, 3)
    image.setAllPixels(
      Array(Array(1, 42, 255), Array(11, 142, 169), Array(21, 242, 80)))
    val rotated = rotate.transform(image)

    val rotatedRef = new GrayscaleImage(3, 3)
    rotatedRef.setAllPixels(
      Array(Array(21, 11, 1), Array(242, 142, 42), Array(80, 169, 255)))

    assertImagesAreSame(rotated, rotatedRef)
  }

  test("biggerWidthThanHeight") {
    val image = new GrayscaleImage(3, 2)
    image.setAllPixels(Array(Array(1, 42, 255), Array(11, 142, 169)))
    val rotated = rotate.transform(image)

    val rotatedRef = new GrayscaleImage(2, 3)
    rotatedRef.setAllPixels(
      Array(Array(11, 1), Array(142, 42), Array(169, 255)))

    assertImagesAreSame(rotated, rotatedRef)
  }

  test("biggerHeightThanWidth") {
    val image = new GrayscaleImage(2, 3)
    image.setAllPixels(Array(Array(42, 255), Array(142, 169), Array(242, 80)))
    val rotated = rotate.transform(image)

    val rotatedRef = new GrayscaleImage(3, 2)
    rotatedRef.setAllPixels(Array(Array(242, 142, 42), Array(80, 169, 255)))

    assertImagesAreSame(rotated, rotatedRef)
  }
}
