package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.ArgumentHandler
import org.mockito.MockitoSugar.{mock, verify}

class HelpArgumentHandlerTest extends ArgumentHandlerTest("help") {

  override def getHandler(controller: Controller): ArgumentHandler =
    new HelpArgumentHandler(controller)

  test("Process argument") {
    val controller = mock[Controller]

    val handler = getHandler(controller)
    handler.processArgument(List[String]().iterator)

    verify(controller).showHelp()
  }

}
