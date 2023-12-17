package asciiArtApp.transformers.media

import asciiArtApp.models.media.Media
import transformers.Transformer

trait MediaToMediaTransformer[S <: Media, T <: Media] extends Transformer[S, T]{}
