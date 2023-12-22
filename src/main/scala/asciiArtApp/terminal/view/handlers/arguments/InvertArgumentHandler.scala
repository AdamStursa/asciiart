package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.EqualityArgumentHandler
import asciiArtApp.transformers.media.image.filters.grayscale.invert.GrayscaleImageInvert

class InvertArgumentHandler(controller: Controller)
    extends EqualityArgumentHandler("--invert") {

  override def processArgument(argsIterator: Iterator[String]): Unit =
    controller.addGrayscaleFilter(new GrayscaleImageInvert)
}
