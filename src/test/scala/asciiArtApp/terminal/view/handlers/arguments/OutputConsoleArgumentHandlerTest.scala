package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.ArgumentHandler
import exporters.text.{StdOutputExporter, TextExporter}
import org.mockito.MockitoSugar.{mock, verify}
import org.mockito.captor.ArgCaptor

class OutputConsoleArgumentHandlerTest
    extends ArgumentHandlerTest("output-console") {

  override def getHandler(controller: Controller): ArgumentHandler =
    new OutputConsoleArgumentHandler(controller)

  test("Not handle --output-file") {
    val controller = mock[Controller]

    val handler = getHandler(controller)

    assert(!handler.handle("--output-file"))
    assert(!handler.handle("output-file"))
  }

  test("Process argument") {
    val controller = mock[Controller]
    val exporter = ArgCaptor[TextExporter]

    val handler = getHandler(controller)
    handler.processArgument(List[String]().iterator)

    verify(controller).addExporter(exporter)
    assert(exporter.value.isInstanceOf[StdOutputExporter])
  }

}
