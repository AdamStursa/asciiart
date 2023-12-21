package exporters.text

import org.scalatest.FunSuite

import java.io.ByteArrayOutputStream

class StreamTextTests extends FunSuite {
  test("Write") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)

    exporter.export("Ahoj")

    assert(stream.toString("UTF-8") == "Ahoj")
  }

  test("Close") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)
    exporter.close()

    assertThrows[Exception](exporter.export("Ahoj"))
  }
}
