package asciiArtApp.transformers.media.image

import asciiArtApp.models.media.image.Image
import asciiArtApp.transformers.media.MediaToMediaTransformer

trait ImageToImageTransformer[S <: Image[_], T <: Image[_]]
    extends MediaToMediaTransformer[S, T] {}
