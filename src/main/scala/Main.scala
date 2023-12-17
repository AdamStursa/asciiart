package Main

import asciiArtApp.console.view.handlers.TempConsoleHandler

object Main extends App {
  private val executor = new TempConsoleHandler()
  executor.parseCommandArgs(args)
  executor.execute()
}
