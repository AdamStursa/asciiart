package asciiArtApp.terminal.view

import asciiArtApp.terminal.controller.Controller
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.never
import org.mockito.MockitoSugar.{mock, verify}
import org.scalatest.FunSuite

class TerminalViewTest extends FunSuite {

  test("Process arguments") {
    val controller = mock[Controller]
    val view = TerminalView(controller)

    view.processArguments(
      List("--image-random", "--table", "linear", "--invert"))
    verify(controller).setLoader(any())(any())
    verify(controller).setASCIITransformer(any())
    verify(controller).showProcessedImage()
    verify(controller, never()).showError(any())
  }

  test("Process arguments with one invalid") {
    val controller = mock[Controller]
    val view = TerminalView(controller)

    view.processArguments(List("--image-random", "--abcd", "--rotate", "90"))
    verify(controller).setLoader(any())(any())
    verify(controller).showError(any())
  }
}
