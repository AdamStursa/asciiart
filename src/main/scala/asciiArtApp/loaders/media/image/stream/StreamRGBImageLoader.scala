package asciiArtApp.loaders.media.image.stream

import asciiArtApp.loaders.media.image.ImageLoader
import asciiArtApp.models.media.image.RGBImage
import asciiArtApp.models.media.pixel.RGBPixel

import java.awt.Color
import java.io.InputStream
import javax.imageio.ImageIO

abstract class StreamRGBImageLoader[I <: InputStream](val stream: InputStream)
    extends ImageLoader[RGBImage] {

  private var closed = false

  def loadFromStream(): RGBImage = {
    if (closed)
      throw new Exception("Input stream has been closed")

    val image = ImageIO.read(stream)

    val result = new RGBImage(image.getWidth(), image.getHeight())

    for (i <- 0 until image.getWidth())
      for (j <- 0 until image.getHeight()) {
        val c = new Color(image.getRGB(i, j))
        result.setPixel(j, i, RGBPixel(c.getRed, c.getGreen, c.getBlue))
      }

    result
  }

  def close(): Unit = {
    if (closed)
      return

    stream.close()
    closed = true
  }
}
