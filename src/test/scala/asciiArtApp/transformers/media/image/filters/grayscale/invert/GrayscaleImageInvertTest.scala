package asciiArtApp.transformers.media.image.filters.grayscale.invert

import asciiArtApp.models.media.image.GrayscaleImage
import asciiArtApp.models.media.pixel.GrayscalePixel
import helpers.ImageComparator
import org.scalatest.FunSuite

class GrayscaleImageInvertTest extends FunSuite with ImageComparator {

  val inverter = new GrayscaleImageInvert

  test("black") {
    val img = new GrayscaleImage(2, 2)
    img.setAllPixels(Array(Array(0, 0), Array(0, 0)))
    val inverted = inverter.transform(img)

    for (x <- 0 until 2)
      for (y <- 0 until 2)
        assert(inverted.getPixel(x, y) == GrayscalePixel(255))
  }

  test("white") {
    val img = new GrayscaleImage(2, 2)
    img.setAllPixels(Array(Array(255, 255), Array(255, 255)))
    val inverted = inverter.transform(img)

    for (x <- 0 until 2)
      for (y <- 0 until 2)
        assert(inverted.getPixel(x, y) == GrayscalePixel(0))
  }

  test("mixed") {
    val img = new GrayscaleImage(2, 2)
    img.setAllPixels(Array(Array(42, 255), Array(142, 169)))
    val inverted = inverter.transform(img)
    val invertedRef = new GrayscaleImage(2, 2)
    invertedRef.setAllPixels(Array(Array(213, 0), Array(113, 86)))

    assertImagesAreSame(inverted, invertedRef)
  }
}
