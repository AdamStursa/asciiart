package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.ArgumentHandler
import exporters.text.{FileOutputExporter, TextExporter}
import helpers.TestWithTextFiles
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.never
import org.mockito.MockitoSugar.{mock, verify}
import org.mockito.captor.ArgCaptor

class OutputFileArgumentHandlerTest
    extends ArgumentHandlerTest("output-file")
    with TestWithTextFiles {

  override def getHandler(controller: Controller): ArgumentHandler =
    new OutputFileArgumentHandler(controller)

  test("Process argument without value") {
    val controller = mock[Controller]

    val handler = getHandler(controller)
    handler.processArgument(List[String]().iterator)

    verify(controller).showError(any())
    verify(controller, never()).addExporter(any())
  }

  test("Process argument witch value") {
    val controller = mock[Controller]
    val exporter = ArgCaptor[TextExporter]
    val path = getTestFile

    ensureDeleted(path)

    val handler = getHandler(controller)
    handler.processArgument(List[String](path).iterator)

    verify(controller).addExporter(exporter)
    assert(exporter.value.isInstanceOf[FileOutputExporter])
  }

}
