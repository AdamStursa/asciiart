package asciiArtApp.terminal.view.renderers

import asciiArtApp.models.media.image.Image

trait ImageRenderer[I <: Image[_], O] {
  def render(image: I): O
}
