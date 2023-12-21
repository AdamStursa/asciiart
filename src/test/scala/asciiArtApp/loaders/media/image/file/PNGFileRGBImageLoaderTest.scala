package asciiArtApp.loaders.media.image.file

import helpers.{ImageComparator, TestWithImageFiles}
import org.scalatest.FunSuite

import java.io.{File, FileOutputStream}
import java.nio.file.Files

class PNGFileRGBImageLoaderTest
    extends FunSuite
    with TestWithImageFiles
    with ImageComparator {

  test("Load") {
    val pngFile = new File(getTestPngFile)

    ensureCreated(pngFile.getPath)
    writePNGToStream(new FileOutputStream(pngFile))

    try {
      val loader = new PNGFileRGBImageLoader(pngFile)
      val image = loader.load()

      assertImagesAreSame(image, getImageReference)
    } finally ensureDeleted(pngFile.getPath)
  }

  test("Invalid mimetype") {
    val jpgFile = new File(getTestJpgFile)

    ensureCreated(jpgFile.getPath)
    writeJPGToStream(new FileOutputStream(jpgFile))
    println(Files.probeContentType(jpgFile.toPath))

    try {
      val loader = new PNGFileRGBImageLoader(jpgFile)
      assertThrows[IllegalArgumentException](loader.load())
    } finally ensureDeleted(jpgFile.getPath)
  }

  test("Close") {
    val pngFile = new File(getTestPngFile)

    ensureCreated(pngFile.getPath)
    writePNGToStream(new FileOutputStream(pngFile))

    try {
      val loader = new PNGFileRGBImageLoader(pngFile)
      loader.close()

      assertThrows[Exception](loader.load())
    } finally ensureDeleted(pngFile.getPath)
  }

  test("File not exists") {
    val pngFile = new File(getTestPngFile)

    ensureDeleted(pngFile.getPath)

    assertThrows[Exception](new PNGFileRGBImageLoader(pngFile))
  }
}
