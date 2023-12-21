package asciiArtApp.console.view.handlers

import asciiArtApp.console.view.renderers.ASCIIImageTextRenderer
import asciiArtApp.loaders.media.MediaLoader
import asciiArtApp.loaders.media.image.file.FileRGBImageLoader
import asciiArtApp.loaders.media.image.random.SimpleRandomRGBImageGenerator
import asciiArtApp.models.media.image.{ASCIIImage, RGBImage}
import asciiArtApp.transformers.media.image.asciiConversion.ASCIIConversionTransformer
import asciiArtApp.transformers.media.image.asciiConversion.table.LinearTableTransformer
import asciiArtApp.transformers.media.image.filters.grayscale.GrayscaleFilter
import asciiArtApp.transformers.media.image.filters.grayscale.flip.{GrayscaleImageFlipX, GrayscaleImageFlipY}
import asciiArtApp.transformers.media.image.filters.grayscale.invert.GrayscaleImageInvert
import asciiArtApp.transformers.media.image.filters.grayscale.mixed.GrayscaleMixedFilter
import asciiArtApp.transformers.media.image.filters.grayscale.rotate.{GrayscaleImageRotate180, GrayscaleImageRotate270, GrayscaleImageRotate90}
import asciiArtApp.transformers.media.image.rgbToGrayscale.RGBToGrayscaleTransformer
import exporters.text.{FileOutputExporter, StdOutputExporter, TextExporter}

import java.io.File
import scala.util.Random
import scala.util.matching.Regex

class TempConsoleHandler {
  private var loader: Option[MediaLoader[_]] = None
  private var convertor: Option[ASCIIConversionTransformer] = None
  private var grayscaleFilters: Seq[GrayscaleFilter] = Seq()
  private var exporters: List[TextExporter] = List()

  def parseCommandArgs(args: Array[String]): Unit = {
    val argsIter: Iterator[String] = args.toIterable.iterator

    while (argsIter.hasNext) argsIter.next() match {
      case "--image-random" =>
        require(loader.isEmpty, "Cannot load image from multiple sources")
        loader = Option(new SimpleRandomRGBImageGenerator(new Random))

      case "--image" =>
        require(loader.isEmpty, "Cannot load image from multiple sources")
        require(argsIter.hasNext, "Argument image has to have value specified")
        val path: String = argsIter.next()
        val file: File = new File(path)
        loader = FileRGBImageLoader(file)

      case "--table" =>
        require(convertor.isEmpty, "Only one conversion method can be defined")
        require(argsIter.hasNext, "Argument table has to have value specified")
        val name: String = argsIter.next()
        convertor = ASCIIConversionTransformer(name)

      case "--custom-table" =>
        require(convertor.isEmpty, "Only one conversion method can be defined")
        require(
          argsIter.hasNext,
          "Argument custom-table has to have value specified")
        val characters: String = argsIter.next()
        convertor = Some(new LinearTableTransformer(characters.toCharArray))

      case "--rotate" =>
        require(argsIter.hasNext, "Argument rotate has to have value specified")
        val degrees: String = argsIter.next()
        val ninetyClockwise: Regex = """^(?!-)\+?90|-270""".r
        val oneEighty: Regex = """-180|\+?180""".r
        val twoSeventyClockwise: Regex = """^(?!-)\+?270|-90""".r
        degrees match {
          case ninetyClockwise() =>
            grayscaleFilters =
              grayscaleFilters.appended(new GrayscaleImageRotate90)
          case oneEighty() =>
            grayscaleFilters =
              grayscaleFilters.appended(new GrayscaleImageRotate180)
          case twoSeventyClockwise() =>
            grayscaleFilters =
              grayscaleFilters.appended(new GrayscaleImageRotate270)
          case _ =>
            throw new AssertionError(
              "Rotate value has to be multiple of 90 between -270 and (+)270")
        }

      case "--flip" =>
        require(argsIter.hasNext, "Argument rotate has to have value specified")
        val axis: String = argsIter.next()
        axis match {
          case "x" =>
            grayscaleFilters =
              grayscaleFilters.appended(new GrayscaleImageFlipX)
          case "y" =>
            grayscaleFilters =
              grayscaleFilters.appended(new GrayscaleImageFlipY)
          case _ => throw new AssertionError("Flip value must be ether x or y")
        }

      case "--invert" =>
        grayscaleFilters = grayscaleFilters.appended(new GrayscaleImageInvert)

      case "--output-console" =>
        exporters = exporters.appended(new StdOutputExporter)

      case "--output-file" =>
        require(
          argsIter.hasNext,
          "Argument output-file has to have value specified")
        val path: String = argsIter.next()
        val outFile: File = new File(path)
        exporters = exporters.appended(new FileOutputExporter(outFile))
    }
  }

  def execute(): Unit = {
    assert(loader.nonEmpty, "Image load method must be specified")
    assert(convertor.nonEmpty, "Image conversion method must be specified")
    assert(exporters.nonEmpty, "At least one output has to be specified")

    val original: RGBImage = loader.get.load().asInstanceOf[RGBImage]
    var grayscale = RGBToGrayscaleTransformer(original)

    if (grayscaleFilters.nonEmpty) {
      val filters: GrayscaleMixedFilter = new GrayscaleMixedFilter(
        grayscaleFilters)
      grayscale = filters.transform(grayscale)
    }

    val ascii: ASCIIImage = convertor.get.transform(grayscale)

    exporters.foreach(_.export(new ASCIIImageTextRenderer().render(ascii)))
  }
}
