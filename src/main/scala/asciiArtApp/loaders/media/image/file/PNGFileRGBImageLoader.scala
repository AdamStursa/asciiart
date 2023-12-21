package asciiArtApp.loaders.media.image.file
import java.io.File

class PNGFileRGBImageLoader(override val file: File)
    extends FileRGBImageLoader(file) {
  override val expectedMimes: Set[String] = PNGFileRGBImageLoader.expectedMimes

}

object PNGFileRGBImageLoader {
  val expectedMimes: Set[String] =
    Set("image/png", "image/x-png", "image/x-citrix-png")
}
