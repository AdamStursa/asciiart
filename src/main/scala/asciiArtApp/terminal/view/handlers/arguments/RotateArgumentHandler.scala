package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.EqualityArgumentHandler
import asciiArtApp.transformers.media.image.filters.grayscale.rotate.{GrayscaleImageRotate180, GrayscaleImageRotate270, GrayscaleImageRotate90}

import scala.util.matching.Regex

class RotateArgumentHandler(controller: Controller)
    extends EqualityArgumentHandler("--rotate") {

  private val ninetyClockwise: Regex = """^(?!-)\+?90|-270""".r
  private val oneEighty: Regex = """-180|\+?180""".r
  private val twoSeventyClockwise: Regex = """^(?!-)\+?270|-90""".r
  private val zero: Regex = """^[+\-]?(?:360|0)""".r

  override def processArgument(argsIterator: Iterator[String]): Unit = {
    if (!argsIterator.hasNext) {
      controller.showError("Argument rotate must have value specified")
      return
    }

    val degrees: String = argsIterator.next()
    degrees match {
      case ninetyClockwise() =>
        controller.addGrayscaleFilter(new GrayscaleImageRotate90)
      case oneEighty() =>
        controller.addGrayscaleFilter(new GrayscaleImageRotate180)
      case twoSeventyClockwise() =>
        controller.addGrayscaleFilter(new GrayscaleImageRotate270)
      case zero() =>
      case _      => controller.showError("Invalid value of degrees")
    }

  }
}
