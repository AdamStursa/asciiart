package asciiArtApp.transformers.media.image.asciiConversion.table

import asciiArtApp.models.media.image.{ASCIIImage, GrayscaleImage}
import asciiArtApp.models.table.asciiTransform.AsciiTransformTable
import asciiArtApp.transformers.media.image.asciiConversion.ASCIIConversionTransformer

abstract class TableTransformer extends ASCIIConversionTransformer {

  val transformTable: AsciiTransformTable
  val name: String

  override def transform(from: GrayscaleImage): ASCIIImage = {
    val asciiImage: ASCIIImage = new ASCIIImage(from.width, from.height)

    for (x <- 0 until from.height)
      for (y <- 0 until from.width)
        asciiImage.setPixel(
          x,
          y,
          transformTable.getGrayscaleAsciiValue(from.getPixel(x, y).value))

    asciiImage
  }
}
