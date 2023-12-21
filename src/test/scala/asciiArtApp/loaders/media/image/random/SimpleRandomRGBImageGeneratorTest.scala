package asciiArtApp.loaders.media.image.random

import helpers.CustomTestRandom
import org.scalatest.FunSuite

class SimpleRandomRGBImageGeneratorTest extends FunSuite {
  test("Load random") {
    val loader = new SimpleRandomRGBImageGenerator(new CustomTestRandom)
    val image = loader.load()
    assert(image.width == 2)
    assert(image.height == 3)

  }
}
