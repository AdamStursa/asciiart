package helpers

import asciiArtApp.models.media.image.Image

trait ImageComparator {
  def assertImagesAreSame(img1: Image[_], img2: Image[_]): Unit = {
    assert(img1.height == img2.height)
    assert(img1.width == img2.width)
    for (x <- 0 until img1.height)
      assert(img1.getAllPixels(x).sameElements(img2.getAllPixels(x)))
  }
}
