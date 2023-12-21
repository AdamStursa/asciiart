package asciiArtApp.transformers.media.image.filters.grayscale.mixed

import asciiArtApp.models.media.image.GrayscaleImage
import asciiArtApp.transformers.media.image.filters.grayscale.flip.{GrayscaleImageFlipX, GrayscaleImageFlipY}
import asciiArtApp.transformers.media.image.filters.grayscale.invert.GrayscaleImageInvert
import asciiArtApp.transformers.media.image.filters.grayscale.rotate.{GrayscaleImageRotate180, GrayscaleImageRotate270, GrayscaleImageRotate90}
import helpers.ImageComparator
import org.scalatest.FunSuite

class GrayscaleMixedFilterTest extends FunSuite with ImageComparator {

  test("Single filter - flip x") {
    val mixedFilter = new GrayscaleMixedFilter(List(new GrayscaleImageFlipX))

    val image = new GrayscaleImage(2, 2)
    image.setAllPixels(Array(Array(42, 255), Array(142, 169)))
    val transformed = mixedFilter.transform(image)

    val transformedRef = new GrayscaleImage(2, 2)
    transformedRef.setAllPixels(Array(Array(142, 169), Array(42, 255)))

    assertImagesAreSame(transformed, transformedRef)
  }

  test("Single filter - flip y") {
    val mixedFilter = new GrayscaleMixedFilter(List(new GrayscaleImageFlipY))

    val image = new GrayscaleImage(3, 3)
    image.setAllPixels(
      Array(Array(1, 42, 255), Array(11, 142, 169), Array(21, 242, 80)))
    val transformed = mixedFilter.transform(image)

    val transformedRef = new GrayscaleImage(3, 3)
    transformedRef.setAllPixels(
      Array(Array(255, 42, 1), Array(169, 142, 11), Array(80, 242, 21)))

    assertImagesAreSame(transformed, transformedRef)
  }

  test("Single filter - rotate 90") {
    val mixedFilter = new GrayscaleMixedFilter(List(new GrayscaleImageRotate90))

    val image = new GrayscaleImage(3, 2)
    image.setAllPixels(Array(Array(1, 42, 255), Array(11, 142, 169)))
    val transformed = mixedFilter.transform(image)

    val transformedRef = new GrayscaleImage(2, 3)
    transformedRef.setAllPixels(
      Array(Array(11, 1), Array(142, 42), Array(169, 255)))

    assertImagesAreSame(transformed, transformedRef)
  }

  test("Single filter - rotate 180") {
    val mixedFilter =
      new GrayscaleMixedFilter(List(new GrayscaleImageRotate180))

    val image = new GrayscaleImage(2, 3)
    image.setAllPixels(Array(Array(42, 255), Array(142, 169), Array(242, 80)))
    val transformed = mixedFilter.transform(image)

    val transformedRef = new GrayscaleImage(2, 3)
    transformedRef.setAllPixels(
      Array(Array(80, 242), Array(169, 142), Array(255, 42)))

    assertImagesAreSame(transformed, transformedRef)
  }

  test("Single filter - rotate 270") {
    val mixedFilter =
      new GrayscaleMixedFilter(List(new GrayscaleImageRotate270))

    val image = new GrayscaleImage(3, 3)
    image.setAllPixels(
      Array(Array(1, 42, 255), Array(11, 142, 169), Array(21, 242, 80)))
    val transformed = mixedFilter.transform(image)

    val transformedRef = new GrayscaleImage(3, 3)
    transformedRef.setAllPixels(
      Array(Array(255, 169, 80), Array(42, 142, 242), Array(1, 11, 21)))

    assertImagesAreSame(transformed, transformedRef)
  }

  test("Single filter - invert") {
    val mixedFilter = new GrayscaleMixedFilter(List(new GrayscaleImageInvert))

    val img = new GrayscaleImage(2, 2)
    img.setAllPixels(Array(Array(42, 255), Array(142, 169)))
    val transformed = mixedFilter.transform(img)
    val transformedRef = new GrayscaleImage(2, 2)
    transformedRef.setAllPixels(Array(Array(213, 0), Array(113, 86)))

    assertImagesAreSame(transformed, transformedRef)
  }

  test("Mixed filters - rotate 180, invert") {
    val mixedFilter = new GrayscaleMixedFilter(
      List(new GrayscaleImageRotate180, new GrayscaleImageInvert))

    val img = new GrayscaleImage(2, 2)
    img.setAllPixels(Array(Array(42, 255), Array(142, 169)))
    val transformed = mixedFilter.transform(img)
    val transformedRef = new GrayscaleImage(2, 2)
    transformedRef.setAllPixels(Array(Array(86, 113), Array(0, 213)))

    assertImagesAreSame(transformed, transformedRef)
  }

  test("Mixed filters - invert, flip x, rotate 90") {
    val mixedFilter = new GrayscaleMixedFilter(
      List(
        new GrayscaleImageInvert,
        new GrayscaleImageFlipX,
        new GrayscaleImageRotate90))

    val img = new GrayscaleImage(2, 2)
    img.setAllPixels(Array(Array(42, 255), Array(142, 169)))
    val transformed = mixedFilter.transform(img)
    val transformedRef = new GrayscaleImage(2, 2)
    transformedRef.setAllPixels(Array(Array(213, 113), Array(0, 86)))

    assertImagesAreSame(transformed, transformedRef)
  }
}
