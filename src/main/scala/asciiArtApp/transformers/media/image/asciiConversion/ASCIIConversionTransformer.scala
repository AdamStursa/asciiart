package asciiArtApp.transformers.media.image.asciiConversion

import asciiArtApp.models.media.image.{ASCIIImage, GrayscaleImage}
import asciiArtApp.transformers.media.image.ImageToImageTransformer
import asciiArtApp.transformers.media.image.asciiConversion.table.{LinearTableTransformer, MoreMidTonesTableTransformer}

trait ASCIIConversionTransformer extends ImageToImageTransformer[GrayscaleImage, ASCIIImage]{

}


object ASCIIConversionTransformer {
  def apply(name: String): Option[ASCIIConversionTransformer] = {
    name match {
      case n if n.toLowerCase == LinearTableTransformer.name.toLowerCase => Some(new LinearTableTransformer())
      case n if n.toLowerCase == MoreMidTonesTableTransformer.name.toLowerCase => Some(new MoreMidTonesTableTransformer())
      case _ => None
    }
  }
}