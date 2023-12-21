package helpers

import java.io.File

trait TestWithFiles {
  val testFolder = "src/test/scala/testFiles/"

  def ensureDeleted(filePath: String): Unit = {
    val file = new File(filePath)
    if (file.exists())
      if (!file.delete())
        throw new Exception("Could not delete " + filePath)
  }

  def ensureCreated(filePath: String): Unit = {
    val file = new File(filePath)

    ensureDeleted(filePath)

    if (!file.createNewFile())
      throw new Exception("Could not create " + filePath)
  }
}
