package asciiArtApp.terminal.view.handlers

import handlers.TextHandler

trait ArgumentHandler extends TextHandler{

  def processArgument(argsIterator: Iterator[String]): Unit
}
