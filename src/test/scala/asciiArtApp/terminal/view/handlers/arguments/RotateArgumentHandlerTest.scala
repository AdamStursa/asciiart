package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.ArgumentHandler
import asciiArtApp.transformers.media.image.filters.grayscale.GrayscaleFilter
import asciiArtApp.transformers.media.image.filters.grayscale.flip.{
  GrayscaleImageFlipX,
  GrayscaleImageFlipY
}
import asciiArtApp.transformers.media.image.filters.grayscale.rotate.{
  GrayscaleImageRotate180,
  GrayscaleImageRotate270,
  GrayscaleImageRotate90
}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{never, verifyNoInteractions, verifyZeroInteractions}
import org.mockito.MockitoSugar.{mock, verify}
import org.mockito.captor.ArgCaptor

class RotateArgumentHandlerTest extends ArgumentHandlerTest("rotate") {

  override def getHandler(controller: Controller): ArgumentHandler =
    new RotateArgumentHandler(controller)

  test("Process argument without value") {
    val controller = mock[Controller]

    val handler = getHandler(controller)
    handler.processArgument(List[String]().iterator)

    verify(controller).showError(any())
    verify(controller, never()).setASCIITransformer(any())
  }

  test("Process argument with value 90") {
    val controller = mock[Controller]
    val filter = ArgCaptor[GrayscaleFilter]

    val handler = getHandler(controller)
    handler.processArgument(List[String]("90").iterator)

    verify(controller).addGrayscaleFilter(filter)
    assert(filter.value.isInstanceOf[GrayscaleImageRotate90])
  }

  test("Process argument with value -90") {
    val controller = mock[Controller]
    val filter = ArgCaptor[GrayscaleFilter]

    val handler = getHandler(controller)
    handler.processArgument(List[String]("-90").iterator)

    verify(controller).addGrayscaleFilter(filter)
    assert(filter.value.isInstanceOf[GrayscaleImageRotate270])
  }

  test("Process argument with value 180") {
    val controller = mock[Controller]
    val filter = ArgCaptor[GrayscaleFilter]

    val handler = getHandler(controller)
    handler.processArgument(List[String]("180").iterator)

    verify(controller).addGrayscaleFilter(filter)
    assert(filter.value.isInstanceOf[GrayscaleImageRotate180])
  }

  test("Process argument with value 360") {
    val controller = mock[Controller]

    val handler = getHandler(controller)
    handler.processArgument(List[String]("360").iterator)

    verifyNoInteractions(controller)
  }

  test("Process argument with invalid value") {
    val controller = mock[Controller]

    val handler = getHandler(controller)
    handler.processArgument(List[String]("abcd").iterator)

    verify(controller).showError(any())
    verify(controller, never()).setASCIITransformer(any())
  }

}
