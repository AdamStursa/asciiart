package asciiArtApp.loaders.media.image.file

import asciiArtApp.loaders.media.image.ImageLoader
import asciiArtApp.loaders.media.image.stream.StreamRGBImageLoader
import asciiArtApp.models.media.image.RGBImage

import java.io.{File, FileInputStream}
import java.nio.file.Files

abstract class FileRGBImageLoader(val source: File) extends ImageLoader[File, RGBImage] with StreamRGBImageLoader[File, FileInputStream] {

  val expectedMimes: Set[String]

  def load(): RGBImage = {
    require(source.exists, f"File ${source.getPath.trim} does not exists")
    require(source.isFile, f"${source.getPath.trim} is not a file")
    require(source.canRead, f"File ${source.getPath.trim} cannot be read")
    require(expectedMimes.contains(Files.probeContentType(source.toPath)), f"Used incorrect loader for ${Files.probeContentType(source.toPath)} mime type")
    val fileStream = new FileInputStream(source)
    loadFromStream(fileStream)
  }
}

object FileRGBImageLoader {
  def apply(file: File): Option[FileRGBImageLoader] = {
    Files.probeContentType(file.toPath) match {
      case t if JPGFileRGBImageLoader.expectedMimes contains t => Some(new JPGFileRGBImageLoader(file))
      case t if PNGFileRGBImageLoader.expectedMimes contains t => Some(new PNGFileRGBImageLoader(file))
      case _ => None
    }
  }
}
