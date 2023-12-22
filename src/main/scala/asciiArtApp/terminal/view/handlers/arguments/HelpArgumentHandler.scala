package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.EqualityArgumentHandler

class HelpArgumentHandler(controller: Controller)
    extends EqualityArgumentHandler("--help") {

  override def processArgument(argsIterator: Iterator[String]): Unit =
    controller.showHelp()
}
