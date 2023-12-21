package asciiArtApp.models.media.image

import asciiArtApp.models.media.pixel.RGBPixel
import helpers.ImageComparator
import org.scalatest.FunSuite

class RGBImageTest extends FunSuite with ImageComparator {

  test("Pixel operations") {
    val image = new RGBImage(2, 2)
    image.setAllPixels(
      Array(
        Array(RGBPixel(0, 0, 0), RGBPixel(0, 1, 0)),
        Array(RGBPixel(0, 0, 1), RGBPixel(1, 0, 1))))
    assert(image.getAllPixels(0)(0) == RGBPixel(0, 0, 0))
    assert(image.getAllPixels(0)(1) == RGBPixel(0, 1, 0))
    assert(image.getAllPixels(1)(0) == RGBPixel(0, 0, 1))
    assert(image.getAllPixels(1)(1) == RGBPixel(1, 0, 1))

    image.setPixel(0, 1, RGBPixel(1, 1, 1))
    assert(image.getAllPixels(0)(1) == RGBPixel(1, 1, 1))
    assert(image.getPixel(0, 1) == RGBPixel(1, 1, 1))
    assert(image.getPixel(1, 1) == RGBPixel(1, 0, 1))
  }

  test("Invalid width and height") {
    assertThrows[IllegalArgumentException](new ASCIIImage(0, 0))
    assertThrows[IllegalArgumentException](new ASCIIImage(-1, 1))
    assertThrows[IllegalArgumentException](new ASCIIImage(1, -1))
  }

  test("Invalid pixel operations") {
    val image = new RGBImage(2, 2)
    assertThrows[AssertionError](image.getAllPixels)
    assertThrows[AssertionError](image.getPixel(0, 0))
    assertThrows[AssertionError](image.getPixel(1, 0))
    assertThrows[IllegalArgumentException](
      image.setPixel(-1, 0, RGBPixel(1, 0, 1)))
    assertThrows[IllegalArgumentException](
      image.setPixel(1, 2, RGBPixel(1, 0, 1)))
    assertThrows[IllegalArgumentException](image.setAllPixels(Array()))
    assertThrows[IllegalArgumentException](
      image.setAllPixels(Array(Array(), Array(), Array())))

    image.setAllPixels(
      Array(
        Array(RGBPixel(0, 0, 0), RGBPixel(0, 1, 0)),
        Array(RGBPixel(0, 0, 1), RGBPixel(1, 0, 1))))
    assertThrows[IllegalArgumentException](image.getPixel(-1, 0))
    assertThrows[IllegalArgumentException](image.getPixel(1, 2))
  }

  test("Create empty image") {
    val image = new RGBImage(1, 1)
    val created = image.createEmpty(2, 2)
    assert(created.width == 2 && created.height == 2)
    assert(created.isInstanceOf[RGBImage])
  }
}
