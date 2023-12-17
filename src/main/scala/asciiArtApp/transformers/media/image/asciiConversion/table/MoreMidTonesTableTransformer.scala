package asciiArtApp.transformers.media.image.asciiConversion.table

import asciiArtApp.models.table.asciiTransform.AsciiTransformTable

class MoreMidTonesTableTransformer extends TableTransformer {

  override val name: String = MoreMidTonesTableTransformer.name

  override val transformTable: AsciiTransformTable = new AsciiTransformTable()
  transformTable.addTransformPair(230, ' ')
  transformTable.addTransformPair(205, ':')
  transformTable.addTransformPair(180, '-')

  transformTable.addTransformPair(170, '[')
  transformTable.addTransformPair(160, '1')
  transformTable.addTransformPair(150, '\\')

  transformTable.addTransformPair(145, '/')
  transformTable.addTransformPair(140, 'f')
  transformTable.addTransformPair(135, 'r')
  transformTable.addTransformPair(130, 'x')
  transformTable.addTransformPair(125, 'n')
  transformTable.addTransformPair(120, 'u')
  transformTable.addTransformPair(115, 'v')
  transformTable.addTransformPair(110, 'c')
  transformTable.addTransformPair(105, 'X')

  transformTable.addTransformPair(95, 'U')
  transformTable.addTransformPair(85, 'C')
  transformTable.addTransformPair(75, 'O')

  transformTable.addTransformPair(50, 'm')
  transformTable.addTransformPair(25, '#')
  transformTable.addTransformPair(0, '@')
}

object MoreMidTonesTableTransformer {
  val name: String = "MoreMidTones"
}