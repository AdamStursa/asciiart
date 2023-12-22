package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.loaders.media.image.random.SimpleRandomRGBImageGenerator
import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.EqualityArgumentHandler

import scala.util.Random

class ImageRandomArgumentHandler(controller: Controller) extends EqualityArgumentHandler("--image-random"){

  override def processArgument(argsIterator: Iterator[String]): Unit = {
    controller.setLoader(new SimpleRandomRGBImageGenerator(new Random))
  }
}
