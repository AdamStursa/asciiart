package asciiArtApp.transformers.media.image.filters.grayscale.defaults

import asciiArtApp.models.media.image.GrayscaleImage
import asciiArtApp.transformers.media.image.filters.ImageFilter
import transformers.defaults.IdentityTransformer

object GrayscaleIdentityFilter extends IdentityTransformer[GrayscaleImage] with ImageFilter[GrayscaleImage]{
}
