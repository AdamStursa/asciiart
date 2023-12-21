package asciiArtApp.loaders.media

import asciiArtApp.models.media.Media
import loaders.Loader

trait MediaLoader[T <: Media] extends Loader[T] {}
