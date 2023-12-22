package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.EqualityArgumentHandler
import exporters.text.StdOutputExporter

class OutputConsoleArgumentHandler(controller: Controller) extends EqualityArgumentHandler("--output-console") {
  override def processArgument(argsIterator: Iterator[String]): Unit = {
    controller.addExporter(new StdOutputExporter)
  }
}
