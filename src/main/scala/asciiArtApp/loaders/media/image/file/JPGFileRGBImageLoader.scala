package asciiArtApp.loaders.media.image.file
import java.io.File

class JPGFileRGBImageLoader(override val source: File) extends FileRGBImageLoader(source) {
  override val expectedMimes: Set[String] = JPGFileRGBImageLoader.expectedMimes
}

object JPGFileRGBImageLoader {
  val expectedMimes: Set[String] = Set("image/jpeg", "image/x-citrix-jpeg")
}