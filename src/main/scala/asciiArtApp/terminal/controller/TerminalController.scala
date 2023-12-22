package asciiArtApp.terminal.controller
import asciiArtApp.loaders.media.MediaLoader
import asciiArtApp.loaders.media.image.ImageLoader
import asciiArtApp.models.media.Media
import asciiArtApp.models.media.image.{ASCIIImage, RGBImage}
import asciiArtApp.terminal.errors.CommandProcessError
import asciiArtApp.terminal.view.pages.TextPage
import asciiArtApp.terminal.view.pages.concrete.{ASCIIImagePage, HelpPage}
import asciiArtApp.terminal.view.pages.generic.ErrorPage
import asciiArtApp.terminal.view.renderers.ASCIIImageTextRenderer
import asciiArtApp.transformers.media.image.asciiConversion.ASCIIConversionTransformer
import asciiArtApp.transformers.media.image.filters.grayscale.GrayscaleFilter
import asciiArtApp.transformers.media.image.filters.grayscale.mixed.GrayscaleMixedFilter
import asciiArtApp.transformers.media.image.rgbToGrayscale.RGBToGrayscaleTransformer
import exporters.text.TextExporter

import scala.reflect.ClassTag

class TerminalController(stdErrorExporter: TextExporter, stdOutputExporter: TextExporter) extends Controller {

  override def setLoader[T <: Media : ClassTag](loader: MediaLoader[T]): Unit = {
    if (this.loader.nonEmpty)
      showError("Cannot load image from multiple sources")
    else
      this.loader = Some(loader)
  }

  override def setASCIITransformer(asciiTransformer: ASCIIConversionTransformer): Unit = {
    if (this.asciiTransformer.nonEmpty)
      showError("Only one conversion method can be defined")
    else
      this.asciiTransformer = Some(asciiTransformer)
  }

  override def setGrayscaleTransformer(grayscaleTransformer: RGBToGrayscaleTransformer): Unit = {
    if (this.grayscaleTransformer.nonEmpty)
      showError("Cannot load image from multiple sources")
    else
      this.grayscaleTransformer = Some(grayscaleTransformer)
  }

  override def addGrayscaleFilter(grayscaleFilter: GrayscaleFilter): Unit = {
    this.grayscaleFilters = this.grayscaleFilters.appended(grayscaleFilter)
  }

  override def addExporter(exporter: TextExporter): Unit = {
    this.exporters = this.exporters.appended(exporter)
  }

  override def showHelp(): Unit = {
    renderStandardOut(new HelpPage)
  }

  override def showError(message: String): Unit = {
    renderError(new ErrorPage(message))
  }

  override def showProcessedImage(): Unit = {
    if (this.loader.isEmpty)
      showError("Image load method must be specified")

    if (this.asciiTransformer.isEmpty)
      showError("Image ascii conversion method must be specified")
    
    if (this.grayscaleTransformer.isEmpty)
      showError("Image grayscale conversion method must be specified")
      
    if (this.exporters.isEmpty)
      showError("At least one output has to be specified")

    val original: RGBImage = this.loader.get.load().asInstanceOf[RGBImage]
    var grayscale = this.grayscaleTransformer.get.transform(original)

    if (this.grayscaleFilters.nonEmpty) {
      val filters: GrayscaleMixedFilter = new GrayscaleMixedFilter(this.grayscaleFilters)
      grayscale = filters.transform(grayscale)
    }

    val ascii: ASCIIImage = this.asciiTransformer.get.transform(grayscale)

    renderWithExporters(new ASCIIImagePage(ascii))
  }

  def renderWithExporters(page: TextPage): Unit = {
    exporters.foreach(_.export(page.render()+"\n"))
  }
  
  def renderStandardOut(page: TextPage): Unit = {
    stdOutputExporter.export(page.render()+"\n")
  }
  
  def renderError(page: ErrorPage): Unit = {
    stdErrorExporter.export(page.render()+"\n")
    throw new CommandProcessError
  }
}
