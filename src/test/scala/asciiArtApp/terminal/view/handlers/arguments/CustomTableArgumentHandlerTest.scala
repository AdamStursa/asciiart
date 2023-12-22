package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.ArgumentHandler
import asciiArtApp.transformers.media.image.asciiConversion.ASCIIConversionTransformer
import asciiArtApp.transformers.media.image.asciiConversion.table.LinearTableTransformer
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.never
import org.mockito.MockitoSugar.{mock, verify}
import org.mockito.captor.ArgCaptor

class CustomTableArgumentHandlerTest
    extends ArgumentHandlerTest("custom-table") {

  override def getHandler(controller: Controller): ArgumentHandler =
    new CustomTableArgumentHandler(controller)

  test("Not handle --table") {
    val controller = mock[Controller]

    val handler = getHandler(controller)

    assert(!handler.handle("--table"))
    assert(!handler.handle("table"))
  }

  test("Process argument without value") {
    val controller = mock[Controller]

    val handler = getHandler(controller)
    handler.processArgument(List[String]().iterator)

    verify(controller).showError(any())
    verify(controller, never()).setASCIITransformer(any())
  }

  test("Process argument") {
    val controller = mock[Controller]
    val transformer = ArgCaptor[ASCIIConversionTransformer]

    val handler = getHandler(controller)
    handler.processArgument(List[String]("abc").iterator)

    verify(controller).setASCIITransformer(transformer)
    assert(transformer.value.isInstanceOf[LinearTableTransformer])
  }

}
