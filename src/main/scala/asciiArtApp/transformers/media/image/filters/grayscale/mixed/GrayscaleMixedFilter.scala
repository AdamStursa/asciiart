package asciiArtApp.transformers.media.image.filters.grayscale.mixed

import asciiArtApp.models.media.image.GrayscaleImage
import asciiArtApp.transformers.media.image.filters.grayscale.GrayscaleFilter
import asciiArtApp.transformers.media.image.filters.mixed.MixedFilter

class GrayscaleMixedFilter(override protected val filters: Seq[GrayscaleFilter])
    extends MixedFilter[GrayscaleImage](filters) {}
