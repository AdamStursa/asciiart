package asciiArtApp.transformers.media.image.rgbToGrayscale

import asciiArtApp.models.media.image.{GrayscaleImage, RGBImage}
import asciiArtApp.models.media.pixel.RGBPixel
import helpers.ImageComparator
import org.scalatest.FunSuite

class RGBToGrayscaleTransformerTest extends FunSuite with ImageComparator {

  test("normal") {
    val rgbImage = new RGBImage(2, 2)
    rgbImage.setAllPixels(
      Array(
        Array(RGBPixel(42, 42, 42), RGBPixel(0, 169, 255)),
        Array(RGBPixel(142, 142, 142), RGBPixel(176, 97, 215))))
    val transformed = new RGBToGrayscaleTransformer().transform(rgbImage)

    val transformedRef = new GrayscaleImage(2, 2)
    transformedRef.setAllPixels(Array(Array(42, 128), Array(142, 134)))

    assertImagesAreSame(transformed, transformedRef)
  }

}
