package asciiArtApp.terminal.view.pages.generic

import asciiArtApp.terminal.view.pages.TextPage

class ErrorPage(message: String) extends TextPage {

  override def render(): String = "Error: "+message
}
