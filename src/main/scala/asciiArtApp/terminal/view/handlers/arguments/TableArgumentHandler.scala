package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.EqualityArgumentHandler
import asciiArtApp.transformers.media.image.asciiConversion.ASCIIConversionTransformer

class TableArgumentHandler(controller: Controller) extends EqualityArgumentHandler("--table"){

  override def processArgument(argsIterator: Iterator[String]): Unit = {
    if (!argsIterator.hasNext)
      controller.showError("Argument table must have value specified")
    else {
      val name = argsIterator.next()
      val transformer = ASCIIConversionTransformer(name)
      if (transformer.isEmpty)
        controller.showError(f"Table named $name does not exists")
      else
        controller.setASCIITransformer(transformer.get)
    }
  }
}
