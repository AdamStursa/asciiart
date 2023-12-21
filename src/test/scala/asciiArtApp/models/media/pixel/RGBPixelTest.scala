package asciiArtApp.models.media.pixel

import org.scalatest.FunSuite

class RGBPixelTest extends FunSuite {

  test("unapply") {
    assert(RGBPixel.unapply(RGBPixel(0, 0, 0)).get == (0, 0, 0))
    assert(RGBPixel.unapply(RGBPixel(90, 120, 230)).get == (90, 120, 230))
    assert(RGBPixel.unapply(RGBPixel(160, 20, 200)).get == (160, 20, 200))
    assert(RGBPixel.unapply(RGBPixel(255, 255, 255)).get == (255, 255, 255))
  }

  test("invalidValues") {
    assertThrows[IllegalArgumentException](RGBPixel(-1, 120, 120))
    assertThrows[IllegalArgumentException](RGBPixel(120, -1, 120))
    assertThrows[IllegalArgumentException](RGBPixel(120, 120, -1))
    assertThrows[IllegalArgumentException](RGBPixel(256, 120, 120))
    assertThrows[IllegalArgumentException](RGBPixel(256, 120, 120))
    assertThrows[IllegalArgumentException](RGBPixel(120, 256, 256))
    assertThrows[IllegalArgumentException](RGBPixel(120, 120, 256))
    assertThrows[IllegalArgumentException](RGBPixel(-1, 120, 256))
    assertThrows[IllegalArgumentException](RGBPixel(-1, -1, 256))
    assertThrows[IllegalArgumentException](RGBPixel(256, -1, 256))
  }
}
