package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.ArgumentHandler
import org.mockito.MockitoSugar.mock
import org.scalatest.FunSuite

abstract class ArgumentHandlerTest(argument: String) extends FunSuite {

  def getHandler(controller: Controller): ArgumentHandler

  test("Handle valid and invalid") {
    val controller = mock[Controller]
    val handler = getHandler(controller)

    assert(handler.handle(s"--$argument"))
    assert(!handler.handle(s"-$argument"))
    assert(!handler.handle(s"$argument"))
    assert(!handler.handle("--"))
    assert(!handler.handle("-"))
  }

}
