package asciiArtApp.transformers.media.image.filters

import asciiArtApp.models.media.image.Image
import asciiArtApp.transformers.media.image.ImageToImageTransformer

trait ImageFilter[I <: Image[_]] extends ImageToImageTransformer[I, I]{

}
