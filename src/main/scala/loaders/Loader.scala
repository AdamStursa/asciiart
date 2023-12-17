package loaders

trait Loader[S, T] {
  val source: S
  def load(): T
}
