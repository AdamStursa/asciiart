package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.ArgumentHandler
import asciiArtApp.transformers.media.image.filters.grayscale.GrayscaleFilter
import asciiArtApp.transformers.media.image.filters.grayscale.invert.GrayscaleImageInvert
import org.mockito.MockitoSugar.{mock, verify}
import org.mockito.captor.ArgCaptor

class InvertArgumentHandlerTest extends ArgumentHandlerTest("invert") {

  override def getHandler(controller: Controller): ArgumentHandler =
    new InvertArgumentHandler(controller)

  test("Process argument") {
    val controller = mock[Controller]
    val filterCap = ArgCaptor[GrayscaleFilter]

    val handler = getHandler(controller)
    handler.processArgument(List[String]().iterator)

    verify(controller).addGrayscaleFilter(filterCap)
    assert(filterCap.value.isInstanceOf[GrayscaleImageInvert])
  }

}
