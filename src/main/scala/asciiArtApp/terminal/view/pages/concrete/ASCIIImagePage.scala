package asciiArtApp.terminal.view.pages.concrete

import asciiArtApp.models.media.image.ASCIIImage
import asciiArtApp.terminal.view.pages.TextPage
import asciiArtApp.terminal.view.renderers.ASCIIImageTextRenderer

class ASCIIImagePage(image: ASCIIImage) extends TextPage {

  override def render(): String = {
    val renderer = new ASCIIImageTextRenderer

    renderer.render(image) + "\n"
  }
}
