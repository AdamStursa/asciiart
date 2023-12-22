package asciiArtApp.terminal.view.handlers

abstract class EqualityArgumentHandler(argumentFormat: String)
    extends ArgumentHandler {
  override def handle(input: String): Boolean = input == argumentFormat
}
