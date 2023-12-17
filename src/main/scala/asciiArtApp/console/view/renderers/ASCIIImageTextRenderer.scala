package asciiArtApp.console.view.renderers

import asciiArtApp.models.media.image.ASCIIImage
import asciiArtApp.models.media.pixel.CharacterPixel.covertToChar

class ASCIIImageTextRenderer extends ImageRenderer[ASCIIImage, String]{

  override def render(image: ASCIIImage): String = {
    image.getAllPixels.map(_.map(covertToChar).mkString).mkString("\n") + "\n"
  }
}
