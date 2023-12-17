package asciiArtApp.loaders.media.image.stream

import asciiArtApp.loaders.media.image.ImageLoader
import asciiArtApp.models.media.image.RGBImage
import asciiArtApp.models.media.pixel.RGBPixel

import java.awt.Color
import java.io.InputStream
import javax.imageio.ImageIO

trait StreamRGBImageLoader[S, I <: InputStream] extends ImageLoader[S, RGBImage] {
  def loadFromStream(stream: I): RGBImage = {
    val image = ImageIO.read(stream)

    val result = RGBImage(image.getWidth(), image.getHeight())

    for (i <- 0 until image.getWidth())
      for (j <- 0 until image.getHeight()) {
        val c = new Color(image.getRGB(i, j))
        result.setPixel(j, i, RGBPixel(c.getRed, c.getGreen, c.getBlue))
      }

    result
  }
}
