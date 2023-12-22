package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.loaders.media.MediaLoader
import asciiArtApp.loaders.media.image.random.SimpleRandomRGBImageGenerator
import asciiArtApp.models.media.image.RGBImage
import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.ArgumentHandler
import org.mockito.ArgumentMatchers.any
import org.mockito.MockitoSugar.{mock, verify}
import org.mockito.captor.ArgCaptor

class ImageRandomArgumentHandlerTest
    extends ArgumentHandlerTest("image-random") {

  override def getHandler(controller: Controller): ArgumentHandler =
    new ImageRandomArgumentHandler(controller)

  test("Not handle --image") {
    val controller = mock[Controller]

    val handler = getHandler(controller)

    assert(!handler.handle("--image"))
    assert(!handler.handle("image"))
  }

  test("Process argument") {
    val controller = mock[Controller]
    val imageLoader = ArgCaptor[MediaLoader[RGBImage]]

    val handler = getHandler(controller)
    handler.processArgument(List[String]().iterator)

    verify(controller).setLoader(imageLoader)(any())
    assert(imageLoader.value.isInstanceOf[SimpleRandomRGBImageGenerator])
  }

}
