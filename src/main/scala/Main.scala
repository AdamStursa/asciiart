package Main

import asciiArtApp.terminal.controller.TerminalController
import asciiArtApp.terminal.view.TerminalView
import asciiArtApp.transformers.media.image.rgbToGrayscale.RGBToGrayscaleTransformer
import exporters.text.{StdErrorExporter, StdOutputExporter}

object Main extends App {
  private val controller =
    new TerminalController(new StdErrorExporter, new StdOutputExporter)
  controller.setGrayscaleTransformer(new RGBToGrayscaleTransformer)
  private val terminalView = TerminalView(controller)
  terminalView.processArguments(args)
}
