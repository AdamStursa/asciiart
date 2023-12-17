package asciiArtApp.models.table

trait Table[R <: Iterable[_]] {
  val rows: R
}
