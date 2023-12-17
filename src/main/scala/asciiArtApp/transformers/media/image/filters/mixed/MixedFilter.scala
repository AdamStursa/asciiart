package asciiArtApp.transformers.media.image.filters.mixed

import asciiArtApp.models.media.image.Image
import asciiArtApp.transformers.media.image.filters.ImageFilter

abstract class MixedFilter[I <: Image[_]](protected val filters: Seq[ImageFilter[I]]) extends ImageFilter[I]{

  override def transform(toTransform: I): I =
    filters.foldLeft(toTransform)((accumulator, filter) => filter.transform(accumulator))
}
