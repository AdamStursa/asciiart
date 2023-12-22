package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.EqualityArgumentHandler
import asciiArtApp.transformers.media.image.asciiConversion.table.LinearTableTransformer

class CustomTableArgumentHandler(controller: Controller)
    extends EqualityArgumentHandler("--custom-table") {

  override def processArgument(argsIterator: Iterator[String]): Unit =
    if (!argsIterator.hasNext)
      controller.showError("Argument custom-table must have value specified")
    else {
      val characters = argsIterator.next()
      controller.setASCIITransformer(
        new LinearTableTransformer(characters.toCharArray))
    }
}
