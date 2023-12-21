package asciiArtApp.models.media.image

import helpers.ImageComparator
import org.scalatest.FunSuite

class ASCIIImageTest extends FunSuite with ImageComparator {

  test("Pixel operations") {
    val image = new ASCIIImage(2, 2)
    image.setAllPixels(Array(Array('a', 'b'), Array('c', 'd')))
    assert(image.getAllPixels(0)(0).value == 'a')
    assert(image.getAllPixels(0)(1).value == 'b')
    assert(image.getAllPixels(1)(0).value == 'c')
    assert(image.getAllPixels(1)(1).value == 'd')

    image.setPixel(0, 1, 'f')
    assert(image.getAllPixels(0)(1).value == 'f')
    assert(image.getPixel(0, 1).value == 'f')
    assert(image.getPixel(1, 1).value == 'd')
  }

  test("Invalid width and height") {
    assertThrows[IllegalArgumentException](new ASCIIImage(0, 0))
    assertThrows[IllegalArgumentException](new ASCIIImage(-1, 1))
    assertThrows[IllegalArgumentException](new ASCIIImage(1, -1))
  }

  test("Invalid pixel operations") {
    val image = new ASCIIImage(2, 2)
    assertThrows[AssertionError](image.getAllPixels)
    assertThrows[AssertionError](image.getPixel(0, 0))
    assertThrows[AssertionError](image.getPixel(1, 0))
    assertThrows[IllegalArgumentException](image.setPixel(-1, 0, 'd'))
    assertThrows[IllegalArgumentException](image.setPixel(1, 2, 'd'))
    assertThrows[IllegalArgumentException](image.setAllPixels(Array()))
    assertThrows[IllegalArgumentException](
      image.setAllPixels(Array(Array(), Array(), Array())))

    image.setAllPixels(Array(Array('a', 'b'), Array('c', 'd')))
    assertThrows[IllegalArgumentException](image.getPixel(-1, 0))
    assertThrows[IllegalArgumentException](image.getPixel(1, 2))
  }

  test("Create empty image") {
    val image = new ASCIIImage(1, 1)
    val created = image.createEmpty(2, 2)
    assert(created.width == 2 && created.height == 2)
    assert(created.isInstanceOf[ASCIIImage])
  }
}
