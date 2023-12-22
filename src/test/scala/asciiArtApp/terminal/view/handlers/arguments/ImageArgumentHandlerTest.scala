package asciiArtApp.terminal.view.handlers.arguments

import asciiArtApp.loaders.media.MediaLoader
import asciiArtApp.loaders.media.image.file.{
  JPGFileRGBImageLoader,
  PNGFileRGBImageLoader
}
import asciiArtApp.models.media.image.RGBImage
import asciiArtApp.terminal.controller.Controller
import asciiArtApp.terminal.view.handlers.ArgumentHandler
import helpers.{TestWithImageFiles, TestWithTextFiles}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.never
import org.mockito.MockitoSugar.{mock, verify}
import org.mockito.captor.ArgCaptor

class ImageArgumentHandlerTest
    extends ArgumentHandlerTest("image")
    with TestWithImageFiles
    with TestWithTextFiles {

  override def getHandler(controller: Controller): ArgumentHandler =
    new ImageArgumentHandler(controller)

  test("Not handle --image-radom") {
    val controller = mock[Controller]

    val handler = getHandler(controller)

    assert(!handler.handle("--image-random"))
    assert(!handler.handle("image-random"))
  }

  test("Process argument with png") {
    val controller = mock[Controller]
    val path = getTestPngFile
    val imageLoader = ArgCaptor[MediaLoader[RGBImage]]

    ensureCreated(path)

    val handler = getHandler(controller)
    handler.processArgument(List[String](path).iterator)

    verify(controller).setLoader(imageLoader)(any())

    assert(imageLoader.value.isInstanceOf[PNGFileRGBImageLoader])

    ensureDeleted(path)
  }

  test("Process argument with jpg") {
    val controller = mock[Controller]
    val path = getTestJpgFile
    val imageLoader = ArgCaptor[MediaLoader[RGBImage]]

    ensureCreated(path)

    val handler = getHandler(controller)
    handler.processArgument(List[String](path).iterator)

    verify(controller).setLoader[RGBImage](imageLoader)(any())
    assert(imageLoader.value.isInstanceOf[JPGFileRGBImageLoader])

    ensureDeleted(path)
  }

  test("Process argument with empty iterator") {
    val controller = mock[Controller]

    val handler = getHandler(controller)
    handler.processArgument(List[String]().iterator)

    verify(controller).showError(any())
    verify(controller, never()).setLoader(any())(any())
  }

  test("Process argument with non existing file") {
    val controller = mock[Controller]
    val path = getTestJpgFile

    ensureDeleted(path)

    val handler = getHandler(controller)
    handler.processArgument(List[String](path).iterator)

    verify(controller).showError(any())
    verify(controller, never()).setLoader(any())(any())
  }

  test("Process argument with unsupported file") {
    val controller = mock[Controller]
    val path = getTestFile

    ensureCreated(path)

    val handler = getHandler(controller)
    handler.processArgument(List[String](path).iterator)

    verify(controller).showError(any())
    verify(controller, never()).setLoader(any())(any())

    ensureDeleted(path)
  }
}
