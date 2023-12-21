package asciiArtApp.models.media.pixel

import org.scalatest.FunSuite

class GrayscalePixelTest extends FunSuite {

  test("unapply") {
    assert(GrayscalePixel.unapply(GrayscalePixel(0)).get == 0)
    assert(GrayscalePixel.unapply(GrayscalePixel(90)).get == 90)
    assert(GrayscalePixel.unapply(GrayscalePixel(160)).get == 160)
    assert(GrayscalePixel.unapply(GrayscalePixel(255)).get == 255)
  }

  test("invalidValues") {
    assert(new GrayscalePixel(120).value == 120)

    assertThrows[IllegalArgumentException](new GrayscalePixel(-1))
    assertThrows[IllegalArgumentException](new GrayscalePixel(256))
    assertThrows[IllegalArgumentException](new GrayscalePixel(-1000))
    assertThrows[IllegalArgumentException](new GrayscalePixel(1000))
  }

  test("implicitConversion") {
    assert(iNeedInt(new GrayscalePixel(120)) == 120)
    assert(iNeedInt(new GrayscalePixel(0)) == 0)
    assert(iNeedInt(new GrayscalePixel(255)) == 255)
    assert(iNeedGrayscalePixel(120) == new GrayscalePixel(120))
    assert(iNeedGrayscalePixel(0) == new GrayscalePixel(0))
    assert(iNeedGrayscalePixel(255) == new GrayscalePixel(255))
  }

  def iNeedInt(value: Int): Int = value
  def iNeedGrayscalePixel(value: GrayscalePixel): GrayscalePixel = value
}
