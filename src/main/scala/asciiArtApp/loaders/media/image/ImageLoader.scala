package asciiArtApp.loaders.media.image

import asciiArtApp.loaders.media.MediaLoader
import asciiArtApp.models.media.image.Image

trait ImageLoader[I <: Image[_]] extends MediaLoader[I] {}
