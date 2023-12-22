package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.ArgumentHandler
import asciiArtApp.transformers.media.image.filters.grayscale.GrayscaleFilter
import asciiArtApp.transformers.media.image.filters.grayscale.flip.{GrayscaleImageFlipX, GrayscaleImageFlipY}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.never
import org.mockito.MockitoSugar.{mock, verify}
import org.mockito.captor.ArgCaptor

class FlipArgumentHandlerTest extends ArgumentHandlerTest("flip") {

  override def getHandler(controller: Controller): ArgumentHandler =
    new FlipArgumentHandler(controller)

  test("Process argument without value") {
    val controller = mock[Controller]

    val handler = getHandler(controller)
    handler.processArgument(List[String]().iterator)

    verify(controller).showError(any())
    verify(controller, never()).setASCIITransformer(any())
  }

  test("Process argument with value x") {
    val controller = mock[Controller]
    val filter = ArgCaptor[GrayscaleFilter]

    val handler = getHandler(controller)
    handler.processArgument(List[String]("x").iterator)

    verify(controller).addGrayscaleFilter(filter)
    assert(filter.value.isInstanceOf[GrayscaleImageFlipX])
  }

  test("Process argument with value y") {
    val controller = mock[Controller]
    val filter = ArgCaptor[GrayscaleFilter]

    val handler = getHandler(controller)
    handler.processArgument(List[String]("y").iterator)

    verify(controller).addGrayscaleFilter(filter)
    assert(filter.value.isInstanceOf[GrayscaleImageFlipY])
  }

  test("Process argument with invalid value") {
    val controller = mock[Controller]

    val handler = getHandler(controller)
    handler.processArgument(List[String]("abcd").iterator)

    verify(controller).showError(any())
    verify(controller, never()).setASCIITransformer(any())
  }

}
