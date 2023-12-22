package asciiArtApp.terminal.view

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.errors.CommandProcessError
import asciiArtApp.terminal.view.handlers.ArgumentHandler
import asciiArtApp.terminal.view.handlers.arguments._
import asciiArtApp.terminal.view.handlers.errors.ArgumentNotFoundHandler

class TerminalView(controller: Controller, handlers: Seq[ArgumentHandler]) {
  def processArguments(args: Seq[String]): Unit = {
    val argsIterator = args.iterator

    try {
      while (argsIterator.hasNext) {
        var handlerNotFound = true

        val arg = argsIterator.next()
        handlers foreach (h =>
          if (handlerNotFound && h.handle(arg)) {
            h.processArgument(argsIterator)
            handlerNotFound = false
          })
      }

      controller.showProcessedImage()
    } catch {
      case _: CommandProcessError => return
    }
  }
}

object TerminalView {
  def apply(controller: Controller): TerminalView =
    new TerminalView(controller, getHandlers(controller))

  private def getHandlers(controller: Controller): Seq[ArgumentHandler] =
    Seq(
      new HelpArgumentHandler(controller),
      new ImageArgumentHandler(controller),
      new ImageRandomArgumentHandler(controller),
      new TableArgumentHandler(controller),
      new CustomTableArgumentHandler(controller),
      new RotateArgumentHandler(controller),
      new FlipArgumentHandler(controller),
      new InvertArgumentHandler(controller),
      new OutputConsoleArgumentHandler(controller),
      new OutputFileArgumentHandler(controller),
      new ArgumentNotFoundHandler(controller)
    )
}
