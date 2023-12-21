package asciiArtApp.models.media.image

import helpers.ImageComparator
import org.scalatest.FunSuite

class GrayscaleImageTest extends FunSuite with ImageComparator {

  test("Pixel operations") {
    val image = new GrayscaleImage(2, 2)
    image.setAllPixels(Array(Array(1, 2), Array(3, 4)))
    assert(image.getAllPixels(0)(0).value == 1)
    assert(image.getAllPixels(0)(1).value == 2)
    assert(image.getAllPixels(1)(0).value == 3)
    assert(image.getAllPixels(1)(1).value == 4)

    image.setPixel(0, 1, 5)
    assert(image.getAllPixels(0)(1).value == 5)
    assert(image.getPixel(0, 1).value == 5)
    assert(image.getPixel(1, 1).value == 4)
  }

  test("Invalid width and height") {
    assertThrows[IllegalArgumentException](new ASCIIImage(0, 0))
    assertThrows[IllegalArgumentException](new ASCIIImage(-1, 1))
    assertThrows[IllegalArgumentException](new ASCIIImage(1, -1))
  }

  test("Invalid pixel operations") {
    val image = new GrayscaleImage(2, 2)
    assertThrows[AssertionError](image.getAllPixels)
    assertThrows[AssertionError](image.getPixel(0, 0))
    assertThrows[AssertionError](image.getPixel(1, 0))
    assertThrows[IllegalArgumentException](image.setPixel(-1, 0, 4))
    assertThrows[IllegalArgumentException](image.setPixel(1, 2, 4))
    assertThrows[IllegalArgumentException](image.setAllPixels(Array()))
    assertThrows[IllegalArgumentException](
      image.setAllPixels(Array(Array(), Array(), Array())))

    image.setAllPixels(Array(Array(1, 2), Array(3, 4)))
    assertThrows[IllegalArgumentException](image.getPixel(-1, 0))
    assertThrows[IllegalArgumentException](image.getPixel(1, 2))
  }

  test("Create empty image") {
    val image = new GrayscaleImage(1, 1)
    val created = image.createEmpty(2, 2)
    assert(created.width == 2 && created.height == 2)
    assert(created.isInstanceOf[GrayscaleImage])
  }
}
