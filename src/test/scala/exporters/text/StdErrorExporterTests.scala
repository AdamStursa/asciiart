package exporters.text

import org.scalatest.FunSuite

import java.io.{ByteArrayOutputStream, PrintStream}

class StdErrorExporterTests extends FunSuite {
  test("Write") {
    val stream = new ByteArrayOutputStream()
    val stdOut = System.err

    System.setErr(new PrintStream(stream))

    val exporter = new StdErrorExporter
    exporter.export("Ahoj")

    System.setErr(stdOut)

    assert(stream.toString("UTF-8") == "Ahoj")
  }

  test("Close") {
    val stream = new ByteArrayOutputStream()
    val stdOut = System.err

    System.setErr(new PrintStream(stream))

    val exporter = new StdErrorExporter
    exporter.close()

    assertThrows[Exception](exporter.export("Ahoj"))

    System.setErr(stdOut)
  }
}
