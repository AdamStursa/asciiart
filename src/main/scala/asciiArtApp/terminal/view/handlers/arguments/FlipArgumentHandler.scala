package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.EqualityArgumentHandler
import asciiArtApp.transformers.media.image.filters.grayscale.flip.{GrayscaleImageFlipX, GrayscaleImageFlipY}

class FlipArgumentHandler(controller: Controller)
    extends EqualityArgumentHandler("--flip") {

  override def processArgument(argsIterator: Iterator[String]): Unit = {
    if (!argsIterator.hasNext) {
      controller.showError("Argument rotate must have value specified")
      return
    }

    val axis: String = argsIterator.next()
    axis.toLowerCase match {
      case "x" => controller.addGrayscaleFilter(new GrayscaleImageFlipX)
      case "y" => controller.addGrayscaleFilter(new GrayscaleImageFlipY)
      case _   => controller.showError("Flip value must be either x or y")
    }
  }
}
