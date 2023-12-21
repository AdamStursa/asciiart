package asciiArtApp.loaders.media.image.file

import helpers.{ImageComparator, TestWithImageFiles}
import org.scalatest.FunSuite

import java.io.{File, FileOutputStream}

class JPGFileRGBImageLoaderTest
    extends FunSuite
    with TestWithImageFiles
    with ImageComparator {

  test("Load") {
    val jpgFile = new File(getTestJpgFile)

    ensureCreated(jpgFile.getPath)
    writeJPGToStream(new FileOutputStream(jpgFile))

    try {
      val loader = new JPGFileRGBImageLoader(jpgFile)
      val image = loader.load()

      // Can not test if images are same because jpg is lossy format
    } finally ensureDeleted(jpgFile.getPath)
  }

  test("Invalid mimetype") {
    val pngFile = new File(getTestPngFile)

    ensureCreated(pngFile.getPath)
    writePNGToStream(new FileOutputStream(pngFile))

    try {
      val loader = new JPGFileRGBImageLoader(pngFile)
      assertThrows[IllegalArgumentException](loader.load())
    } finally ensureDeleted(pngFile.getPath)
  }

  test("Close") {
    val jpgFile = new File(getTestJpgFile)

    ensureCreated(jpgFile.getPath)
    writeJPGToStream(new FileOutputStream(jpgFile))

    try {
      val loader = new JPGFileRGBImageLoader(jpgFile)
      loader.close()

      assertThrows[Exception](loader.load())
    } finally ensureDeleted(jpgFile.getPath)
  }

  test("File not exists") {
    val jpgFile = new File(getTestJpgFile)

    ensureDeleted(jpgFile.getPath)

    assertThrows[Exception](new JPGFileRGBImageLoader(jpgFile))
  }
}
