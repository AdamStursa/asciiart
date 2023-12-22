package asciiArtApp.terminal.view.handlers.errors

import asciiArtApp.terminal.controller.Controller
import org.mockito.ArgumentMatchers.any
import org.mockito.MockitoSugar.{mock, verify}
import org.scalatest.FunSuite

class ArgumentNotFoundHandlerTest extends FunSuite {
  test("Anything handles") {
    val controller = mock[Controller]
    val handler = new ArgumentNotFoundHandler(controller)

    assert(handler.handle(""))
    assert(handler.handle("absdf"))
    assert(handler.handle("femovinsv oveenvis ocoevn"))
  }

  test("Shows error") {
    val controller = mock[Controller]
    val handler = new ArgumentNotFoundHandler(controller)

    handler.processArgument(List().iterator)
    verify(controller).showError(any())
  }
}
