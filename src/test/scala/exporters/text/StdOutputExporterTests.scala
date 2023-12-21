package exporters.text

import org.scalatest.FunSuite

import java.io.{ByteArrayOutputStream, PrintStream}

class StdOutputExporterTests extends FunSuite {
  test("Write") {
    val stream = new ByteArrayOutputStream()
    val stdOut = System.out

    System.setOut(new PrintStream(stream))

    val exporter = new StdOutputExporter
    exporter.export("Ahoj")

    System.setOut(stdOut)

    assert(stream.toString("UTF-8") == "Ahoj")
  }

  test("Close") {
    val stream = new ByteArrayOutputStream()
    val stdOut = System.out

    System.setOut(new PrintStream(stream))

    val exporter = new StdOutputExporter
    exporter.close()

    assertThrows[Exception](exporter.export("Ahoj"))

    System.setOut(stdOut)
  }
}
