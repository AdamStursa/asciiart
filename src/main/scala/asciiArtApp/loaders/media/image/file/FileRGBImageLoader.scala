package asciiArtApp.loaders.media.image.file

import asciiArtApp.loaders.media.image.stream.StreamRGBImageLoader
import asciiArtApp.models.media.image.RGBImage

import java.io.{File, FileInputStream}
import java.nio.file.Files

abstract class FileRGBImageLoader(val file: File)
    extends StreamRGBImageLoader(new FileInputStream(file)) {

  val expectedMimes: Set[String]

  override def load(): RGBImage = {
    require(
      expectedMimes.contains(Files.probeContentType(file.toPath)),
      f"Used incorrect loader for ${Files.probeContentType(file.toPath)} mime type")
    loadFromStream()
  }
}

object FileRGBImageLoader {
  def apply(file: File): Option[FileRGBImageLoader] =
    Files.probeContentType(file.toPath) match {
      case t if JPGFileRGBImageLoader.expectedMimes contains t =>
        Some(new JPGFileRGBImageLoader(file))
      case t if PNGFileRGBImageLoader.expectedMimes contains t =>
        Some(new PNGFileRGBImageLoader(file))
      case _ => None
    }
}
