package asciiArtApp.terminal.view.pages.concrete

import asciiArtApp.terminal.view.pages.TextPage

class HelpPage extends TextPage{

  override def render(): String =
    """
      |Tool for turning image into ASCII art
      |
      | --help : shows this page
      |
      |mandatory arguments:
      |
      | --image <path> : loads image from path
      | --image-random : creates a random image
      |  (Only one --image-* argument can be used)
      |
      | --table <name> : uses selected table for transformation to ASCII image
      | --custom-table <characters> : uses inputted characters in linear table
      |  (Only one of --*table arguments can be used)
      |
      |  --output-console : prints generated ASCII image into console
      |  --output-file <path> : prints generated ASCII image into selected file
      |   (At least one of --output-* arguments must be used)
      |
      |optional arguments:
      |
      | --rotate <degrees> : rotates image by x degrees clockwise
      |   (<degrees> must be a multiply of 90 in range (-270; 270))
      | --flip x|y : flip image on x or y axis
      | --invert : inverts grayscale values of the image
      |""".stripMargin
}
