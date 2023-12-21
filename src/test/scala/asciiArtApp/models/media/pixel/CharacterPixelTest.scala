package asciiArtApp.models.media.pixel

import org.scalatest.FunSuite

class CharacterPixelTest extends FunSuite {

  test("unapply") {
    assert(CharacterPixel.unapply(CharacterPixel('a')).get == 'a')
    assert(CharacterPixel.unapply(CharacterPixel('b')).get == 'b')
    assert(CharacterPixel.unapply(CharacterPixel(' ')).get == ' ')
  }

  test("implicitConversion") {
    assert(iNeedChar(new CharacterPixel('a')) == 'a')
    assert(iNeedChar(new CharacterPixel(' ')) == ' ')
    assert(iNeedCharacterPixel('a') == new CharacterPixel('a'))
    assert(iNeedCharacterPixel(' ') == new CharacterPixel(' '))
  }

  def iNeedChar(char: Char): Char = char
  def iNeedCharacterPixel(characterPixel: CharacterPixel): CharacterPixel =
    characterPixel
}
