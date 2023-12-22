package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.ArgumentHandler
import asciiArtApp.transformers.media.image.asciiConversion.ASCIIConversionTransformer
import asciiArtApp.transformers.media.image.asciiConversion.table.{
  LinearTableTransformer,
  MoreMidTonesTableTransformer
}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.never
import org.mockito.MockitoSugar.{mock, verify}
import org.mockito.captor.ArgCaptor

class TableArgumentHandlerTest extends ArgumentHandlerTest("table") {

  override def getHandler(controller: Controller): ArgumentHandler =
    new TableArgumentHandler(controller)

  test("Not handle --custom-table") {
    val controller = mock[Controller]

    val handler = getHandler(controller)

    assert(!handler.handle("--custom-table"))
    assert(!handler.handle("custom-table"))
  }

  test("Process argument with value linear") {
    val controller = mock[Controller]
    val transformer = ArgCaptor[ASCIIConversionTransformer]

    val handler = getHandler(controller)
    handler.processArgument(List[String]("linear").iterator)

    verify(controller).setASCIITransformer(transformer)
    assert(transformer.value.isInstanceOf[LinearTableTransformer])
  }

  test("Process argument with value moremidtones") {
    val controller = mock[Controller]
    val transformer = ArgCaptor[ASCIIConversionTransformer]

    val handler = getHandler(controller)
    handler.processArgument(List[String]("moremidtones").iterator)

    verify(controller).setASCIITransformer(transformer)
    assert(transformer.value.isInstanceOf[MoreMidTonesTableTransformer])
  }

  test("Process argument without value") {
    val controller = mock[Controller]

    val handler = getHandler(controller)
    handler.processArgument(List[String]().iterator)

    verify(controller).showError(any())
    verify(controller, never()).setASCIITransformer(any())
  }

  test("Process argument with invalid value") {
    val controller = mock[Controller]

    val handler = getHandler(controller)
    handler.processArgument(List[String]("abc").iterator)

    verify(controller).showError(any())
    verify(controller, never()).setASCIITransformer(any())
  }

}
