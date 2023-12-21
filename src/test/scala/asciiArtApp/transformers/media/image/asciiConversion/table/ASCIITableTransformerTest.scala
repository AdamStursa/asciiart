package asciiArtApp.transformers.media.image.asciiConversion.table

import asciiArtApp.models.media.image.{ASCIIImage, GrayscaleImage}
import helpers.ImageComparator
import org.scalatest.FunSuite

class ASCIITableTransformerTest extends FunSuite with ImageComparator {

  test("linear") {
    val transformer = new LinearTableTransformer()
    val image = new GrayscaleImage(6, 2)
    image.setAllPixels(
      Array(
        Array(0, 14, 42, 69, 100, 113),
        Array(124, 142, 178, 201, 222, 255)))

    val transformed = transformer.transform(image)
    val transformerRef = new ASCIIImage(6, 2)
    transformerRef.setAllPixels(
      Array(
        Array('$', '8', 'a', 'm', 'U', 'c'),
        Array('n', 't', ']', '<', ';', ' ')))

    assertImagesAreSame(transformerRef, transformed)
  }

  test("moreMidTones") {
    val transformer = new MoreMidTonesTableTransformer
    val image = new GrayscaleImage(6, 2)
    image.setAllPixels(
      Array(
        Array(0, 14, 42, 69, 100, 113),
        Array(124, 142, 178, 201, 222, 255)))

    val transformed = transformer.transform(image)
    val transformerRef = new ASCIIImage(6, 2)
    transformerRef.setAllPixels(
      Array(
        Array('@', '@', '#', 'm', 'U', 'c'),
        Array('u', 'f', '[', '-', ':', ' ')))

    assertImagesAreSame(transformerRef, transformed)
  }

  test("customLinear") {
    val transformer = new LinearTableTransformer(Array('$', ' '))
    val image = new GrayscaleImage(6, 2)
    image.setAllPixels(
      Array(
        Array(0, 14, 42, 69, 100, 113),
        Array(128, 142, 178, 201, 222, 255)))

    val transformed = transformer.transform(image)
    val transformerRef = new ASCIIImage(6, 2)
    transformerRef.setAllPixels(
      Array(
        Array('$', '$', '$', '$', '$', '$'),
        Array(' ', ' ', ' ', ' ', ' ', ' ')))

    assertImagesAreSame(transformerRef, transformed)
  }

  test("invalidCustomLinear") {
    assertThrows[IllegalArgumentException](new LinearTableTransformer(Array()))
    assertThrows[IllegalArgumentException](
      new LinearTableTransformer(Array.fill[Char](256)('a')))
  }
}
