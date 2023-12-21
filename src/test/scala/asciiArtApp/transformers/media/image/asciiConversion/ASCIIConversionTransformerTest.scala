package asciiArtApp.transformers.media.image.asciiConversion

import asciiArtApp.transformers.media.image.asciiConversion.table.{LinearTableTransformer, MoreMidTonesTableTransformer}
import org.scalatest.FunSuite

class ASCIIConversionTransformerTest extends FunSuite {

  test("findByLowercase") {
    assert(ASCIIConversionTransformer("linear").nonEmpty)
    ASCIIConversionTransformer("linear").get
      .asInstanceOf[LinearTableTransformer]
    assert(ASCIIConversionTransformer("moremidtones").nonEmpty)
    ASCIIConversionTransformer("moremidtones").get
      .asInstanceOf[MoreMidTonesTableTransformer]
  }

  test("findByUppercase") {
    assert(ASCIIConversionTransformer("LINEAR").nonEmpty)
    ASCIIConversionTransformer("LINEAR").get
      .asInstanceOf[LinearTableTransformer]
    assert(ASCIIConversionTransformer("MOREMIDTONES").nonEmpty)
    ASCIIConversionTransformer("MOREMIDTONES").get
      .asInstanceOf[MoreMidTonesTableTransformer]
  }

  test("findByMixed") {
    assert(ASCIIConversionTransformer("LiNeAR").nonEmpty)
    ASCIIConversionTransformer("LiNeAR").get
      .asInstanceOf[LinearTableTransformer]
    assert(ASCIIConversionTransformer("MoREmiDtoNeS").nonEmpty)
    ASCIIConversionTransformer("MoREmiDtoNeS").get
      .asInstanceOf[MoreMidTonesTableTransformer]
  }

  test("nonExisting") {
    assert(ASCIIConversionTransformer("").isEmpty)
    assert(ASCIIConversionTransformer("abcd").isEmpty)
    assert(ASCIIConversionTransformer("jvio shv eoin").isEmpty)
  }

}
