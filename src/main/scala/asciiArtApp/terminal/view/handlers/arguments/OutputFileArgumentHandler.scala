package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.EqualityArgumentHandler
import exporters.text.FileOutputExporter

import java.io.File

class OutputFileArgumentHandler(controller: Controller)
    extends EqualityArgumentHandler("--output-file") {

  override def processArgument(argsIterator: Iterator[String]): Unit = {
    if (!argsIterator.hasNext) {
      controller.showError("Argument output file must have value specified")
      return
    }

    val path: String = argsIterator.next()
    val outFile: File = new File(path)
    controller.addExporter(new FileOutputExporter(outFile))
  }
}
