package asciiArtApp.loaders.media.image.file

import helpers.{TestWithImageFiles, TestWithTextFiles}
import org.scalatest.FunSuite

import java.io.{File, FileOutputStream}

class FileRGBImageLoaderTest
    extends FunSuite
    with TestWithImageFiles
    with TestWithTextFiles {

  test("Get loader based on png file") {
    val pngFile = new File(getTestPngFile)

    ensureCreated(pngFile.getPath)
    writePNGToStream(new FileOutputStream(pngFile))

    assert(FileRGBImageLoader(pngFile).nonEmpty)
    FileRGBImageLoader(pngFile).get.asInstanceOf[PNGFileRGBImageLoader]

    ensureDeleted(pngFile.getPath)
  }

  test("Get loader based on jpg file") {
    val jpgFile = new File(getTestJpgFile)

    ensureCreated(jpgFile.getPath)
    writePNGToStream(new FileOutputStream(jpgFile))

    assert(FileRGBImageLoader(jpgFile).nonEmpty)
    FileRGBImageLoader(jpgFile).get.asInstanceOf[JPGFileRGBImageLoader]

    ensureDeleted(jpgFile.getPath)
  }

  test("Get loader based on jpg file (invalid input)") {
    val path = getTestFile
    ensureCreated(path)

    assert(FileRGBImageLoader(new File(path)).isEmpty)

    ensureDeleted(path)
  }
}
