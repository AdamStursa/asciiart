package asciiArtApp.models.media.pixel

import scala.language.implicitConversions

case class CharacterPixel (value: Char) extends Pixel {

}

object CharacterPixel {
  implicit def covertFromChar(char: Char): CharacterPixel = new CharacterPixel(char)

  implicit def covertToChar(characterPixel: CharacterPixel): Char = characterPixel.value
}
