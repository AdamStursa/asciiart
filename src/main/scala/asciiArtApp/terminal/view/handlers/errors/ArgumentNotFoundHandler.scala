package asciiArtApp.terminal.view.handlers.errors

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.ArgumentHandler

class ArgumentNotFoundHandler(controller: Controller) extends ArgumentHandler {

  override def processArgument(argsIterator: Iterator[String]): Unit =
    controller.showError(f"Invalid argument passed in")

  override def handle(input: String): Boolean = true
}
