package asciiArtApp.loaders.media

import asciiArtApp.models.media.Media
import loaders.Loader

trait MediaLoader[S, T <: Media] extends Loader[S, T]{

}
