package asciiArtApp.loaders.media.image

import asciiArtApp.loaders.media.MediaLoader
import asciiArtApp.models.media.image.Image

trait ImageLoader[S, I <: Image[_]] extends MediaLoader[S, I] {}
