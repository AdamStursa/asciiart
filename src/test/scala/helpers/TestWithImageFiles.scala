package helpers

import asciiArtApp.models.media.image.RGBImage
import asciiArtApp.models.media.pixel.RGBPixel

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.OutputStream
import java.util.UUID
import javax.imageio.ImageIO

trait TestWithImageFiles extends TestWithFiles {

  def getTestPngFile: String = testFolder + UUID.randomUUID().toString + ".png"

  def getTestJpgFile: String = testFolder + UUID.randomUUID().toString + ".jpeg"

  def getBufferedImage: BufferedImage = {
    val img = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB)
    img.setRGB(0, 0, Color.BLACK.getRGB)
    img.setRGB(0, 1, Color.WHITE.getRGB)
    img.setRGB(0, 2, Color.BLACK.getRGB)
    img.setRGB(1, 0, Color.WHITE.getRGB)
    img.setRGB(1, 1, Color.BLACK.getRGB)
    img.setRGB(1, 2, Color.WHITE.getRGB)
    img.setRGB(2, 0, Color.BLACK.getRGB)
    img.setRGB(2, 1, Color.WHITE.getRGB)
    img.setRGB(2, 2, Color.BLACK.getRGB)
    img
  }

  def getImageReference: RGBImage = {
    val img = new RGBImage(3, 3)
    img.setPixel(0, 0, RGBPixel(0, 0, 0))
    img.setPixel(0, 1, RGBPixel(255, 255, 255))
    img.setPixel(0, 2, RGBPixel(0, 0, 0))
    img.setPixel(1, 0, RGBPixel(255, 255, 255))
    img.setPixel(1, 1, RGBPixel(0, 0, 0))
    img.setPixel(1, 2, RGBPixel(255, 255, 255))
    img.setPixel(2, 0, RGBPixel(0, 0, 0))
    img.setPixel(2, 1, RGBPixel(255, 255, 255))
    img.setPixel(2, 2, RGBPixel(0, 0, 0))
    img
  }

  def writePNGToStream(outputStream: OutputStream): Unit = {
    ImageIO.write(getBufferedImage, "png", outputStream)
    outputStream.flush()
    outputStream.close()
  }

  def writeJPGToStream(outputStream: OutputStream): Unit = {
    ImageIO.write(getBufferedImage, "jpg", outputStream)
    outputStream.flush()
    outputStream.close()
  }
}
