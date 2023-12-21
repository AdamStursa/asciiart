package helpers

import java.util.UUID
import scala.io.Source

trait TestWithTextFiles extends TestWithFiles {
  def getTestFile: String = testFolder + UUID.randomUUID().toString + ".txt"

  def assertFileContent(filePath: String, content: String): Unit = {
    val source = Source.fromFile(filePath)
    val text = source.mkString

    source.close()

    assert(text == content)
  }
}
