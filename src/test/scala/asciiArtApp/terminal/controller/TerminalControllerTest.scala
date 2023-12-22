package asciiArtApp.terminal.controller

import asciiArtApp.loaders.media.image.ImageLoader
import asciiArtApp.models.media.image.{ASCIIImage, RGBImage}
import asciiArtApp.models.media.pixel.RGBPixel
import asciiArtApp.terminal.errors.CommandProcessError
import asciiArtApp.terminal.view.pages.concrete.{ASCIIImagePage, HelpPage}
import asciiArtApp.terminal.view.pages.generic.ErrorPage
import asciiArtApp.transformers.media.image.asciiConversion.table.LinearTableTransformer
import asciiArtApp.transformers.media.image.filters.grayscale.flip.GrayscaleImageFlipX
import asciiArtApp.transformers.media.image.filters.grayscale.invert.GrayscaleImageInvert
import asciiArtApp.transformers.media.image.filters.grayscale.rotate.GrayscaleImageRotate90
import asciiArtApp.transformers.media.image.rgbToGrayscale.RGBToGrayscaleTransformer
import exporters.text.StreamTextExporter
import org.scalatest.FunSuite

import java.io.ByteArrayOutputStream

class TerminalControllerTest extends FunSuite {
  class TestLoader extends ImageLoader[RGBImage] {
    override def load(): RGBImage = {
      val img = new RGBImage(3, 2)
      img.setAllPixels(
        Array(
          Array(RGBPixel(1, 1, 1), RGBPixel(253, 253, 253), RGBPixel(2, 2, 2)),
          Array(
            RGBPixel(254, 254, 254),
            RGBPixel(3, 3, 3),
            RGBPixel(255, 255, 255))))
      img
    }
  }

  test("Show help") {
    val outputStream = new ByteArrayOutputStream()
    val errorStream = new ByteArrayOutputStream()
    val testOutputExporter = new StreamTextExporter(outputStream)
    val testErrorExporter = new StreamTextExporter(errorStream)

    val controller =
      new TerminalController(testErrorExporter, testOutputExporter)
    controller.showHelp()

    assert(outputStream.toString == new HelpPage().render())
    assert(errorStream.toString == "")

    testOutputExporter.close()
    testErrorExporter.close()
  }

  test("Show processed image") {
    val outputStream = new ByteArrayOutputStream()
    val errorStream = new ByteArrayOutputStream()
    val testOutputExporter = new StreamTextExporter(outputStream)
    val testErrorExporter = new StreamTextExporter(errorStream)

    val controller =
      new TerminalController(testErrorExporter, testOutputExporter)
    controller.setLoader(new TestLoader)
    controller.setASCIITransformer(new LinearTableTransformer(Array('@', ' ')))
    controller.setGrayscaleTransformer(new RGBToGrayscaleTransformer)
    controller.addExporter(testOutputExporter)
    controller.addGrayscaleFilter(new GrayscaleImageRotate90)
    controller.addGrayscaleFilter(new GrayscaleImageFlipX)
    controller.addGrayscaleFilter(new GrayscaleImageInvert)

    controller.showProcessedImage()

    val expectedRes = new ASCIIImage(2, 3)
    expectedRes.setAllPixels(
      Array(Array('@', ' '), Array(' ', '@'), Array('@', ' ')))

    assert(outputStream.toString == new ASCIIImagePage(expectedRes).render())
    assert(errorStream.toString == "")

    testOutputExporter.close()
    testErrorExporter.close()
  }

  test("Show error") {
    val outputStream = new ByteArrayOutputStream()
    val errorStream = new ByteArrayOutputStream()
    val testOutputExporter = new StreamTextExporter(outputStream)
    val testErrorExporter = new StreamTextExporter(errorStream)

    val controller =
      new TerminalController(testErrorExporter, testOutputExporter)
    assertThrows[CommandProcessError](controller.showError("Test"))
    assert(errorStream.toString == new ErrorPage("Test").render())
    assert(outputStream.toString == "")

    testOutputExporter.close()
    testErrorExporter.close()
  }

  test("Invalid controller actions") {
    val outputStream = new ByteArrayOutputStream()
    val errorStream = new ByteArrayOutputStream()
    val testOutputExporter = new StreamTextExporter(outputStream)
    val testErrorExporter = new StreamTextExporter(errorStream)

    val controller =
      new TerminalController(testErrorExporter, testOutputExporter)

    assertThrows[CommandProcessError](controller.showProcessedImage())
    assert(errorStream.toString != "")
    assert(outputStream.toString == "")
    errorStream.reset()

    controller.setLoader(new TestLoader)
    assertThrows[CommandProcessError](controller.setLoader(new TestLoader))
    assert(errorStream.toString != "")
    assert(outputStream.toString == "")
    errorStream.reset()

    assertThrows[CommandProcessError](controller.showProcessedImage())
    assert(errorStream.toString != "")
    assert(outputStream.toString == "")
    errorStream.reset()

    controller.setASCIITransformer(new LinearTableTransformer)
    assertThrows[CommandProcessError](
      controller.setASCIITransformer(new LinearTableTransformer))
    assert(errorStream.toString != "")
    assert(outputStream.toString == "")
    errorStream.reset()

    assertThrows[CommandProcessError](controller.showProcessedImage())
    assert(errorStream.toString != "")
    assert(outputStream.toString == "")
    errorStream.reset()

    controller.setGrayscaleTransformer(new RGBToGrayscaleTransformer)
    assertThrows[CommandProcessError](
      controller.setGrayscaleTransformer(new RGBToGrayscaleTransformer))
    assert(errorStream.toString != "")
    assert(outputStream.toString == "")
    errorStream.reset()

    assertThrows[CommandProcessError](controller.showProcessedImage())
    assert(errorStream.toString != "")
    assert(outputStream.toString == "")
    errorStream.reset()

    testOutputExporter.close()
    testErrorExporter.close()
  }
}
