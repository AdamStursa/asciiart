package asciiArtApp.terminal.view.pages

trait Page[T] {
  def render(): T
}
