package asciiArtApp.terminal.controller

import asciiArtApp.loaders.media.MediaLoader
import asciiArtApp.loaders.media.image.ImageLoader
import asciiArtApp.models.media.Media
import asciiArtApp.models.media.image.{GrayscaleImage, RGBImage}
import asciiArtApp.transformers.media.image.rgbToGrayscale.RGBToGrayscaleTransformer
import asciiArtApp.transformers.media.image.asciiConversion.ASCIIConversionTransformer
import asciiArtApp.transformers.media.image.filters.grayscale.GrayscaleFilter
import exporters.text.TextExporter

import scala.reflect.ClassTag

trait Controller {
  protected var loader: Option[MediaLoader[_]] = None
  protected var grayscaleTransformer: Option[RGBToGrayscaleTransformer] = None
  protected var asciiTransformer: Option[ASCIIConversionTransformer] = None
  protected var grayscaleFilters: Seq[GrayscaleFilter] = Seq()
  protected var exporters: List[TextExporter] = List()

  def setLoader[T <: Media : ClassTag](loader: MediaLoader[T]): Unit
  def setASCIITransformer(asciiTransformer: ASCIIConversionTransformer): Unit
  def setGrayscaleTransformer(grayscaleTransformer: RGBToGrayscaleTransformer): Unit
  def addGrayscaleFilter(grayscaleFilter: GrayscaleFilter): Unit
  def addExporter(exporter: TextExporter): Unit

  def showHelp(): Unit
  def showError(message: String): Unit
  def showProcessedImage(): Unit
}
