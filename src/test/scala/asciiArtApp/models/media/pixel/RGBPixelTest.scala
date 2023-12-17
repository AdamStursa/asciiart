package asciiArtApp.models.media.pixel

import org.scalatest.FunSuite

class RGBPixelTest extends FunSuite{
  test("getGrayscaleValue"){
    val pixel1 = new RGBPixel(0,0,0)
    assert(pixel1.grayscaleValue == 0)

    val pixel2 = new RGBPixel(90, 120, 230)
    assert(pixel2.grayscaleValue == 123)

    val pixel3 = new RGBPixel(160, 20, 200)
    assert(pixel3.grayscaleValue == 81)

    val pixel4 = new RGBPixel(220, 180, 240)
    assert(pixel4.grayscaleValue == 198)

    val pixel5 = new RGBPixel(255, 255, 255)
    assert(pixel5.grayscaleValue == 255)
  }

  test("invalidValues"){
    assertThrows[IllegalArgumentException](new RGBPixel(-1, 120, 120))
    assertThrows[IllegalArgumentException](new RGBPixel(120, -1, 120))
    assertThrows[IllegalArgumentException](new RGBPixel(120, 120, -1))
    assertThrows[IllegalArgumentException](new RGBPixel(256, 120, 120))
    assertThrows[IllegalArgumentException](new RGBPixel(256, 120, 120))
    assertThrows[IllegalArgumentException](new RGBPixel(120, 256, 256))
    assertThrows[IllegalArgumentException](new RGBPixel(120, 120, 256))
    assertThrows[IllegalArgumentException](new RGBPixel(-1, 120, 256))
    assertThrows[IllegalArgumentException](new RGBPixel(-1, -1, 256))
    assertThrows[IllegalArgumentException](new RGBPixel(256, -1, 256))
  }
}
