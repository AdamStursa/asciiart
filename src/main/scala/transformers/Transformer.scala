package transformers

trait Transformer[F, T] {
  def transform(toTransform: F): T
}
