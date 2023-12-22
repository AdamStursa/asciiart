package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.loaders.media.image.file.FileRGBImageLoader
import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.EqualityArgumentHandler
import asciiArtApp.terminal.view.pages.generic.ErrorPage
import exporters.text.StdErrorExporter

import java.io.File


class ImageArgumentHandler(controller: Controller) extends EqualityArgumentHandler("--image") {

  override def processArgument(argsIterator: Iterator[String]): Unit = {
    if (!argsIterator.hasNext)
      controller.showError("Argument image must have value specified")
    else {
      val file = new File(argsIterator.next())
      val loader = FileRGBImageLoader(file)
      if (loader.isEmpty)
        controller.showError("Input file format is not supported")
      controller.setLoader(loader.get)
    }
  }


}
