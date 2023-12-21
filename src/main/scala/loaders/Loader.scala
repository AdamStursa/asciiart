package loaders

trait Loader[T] {
  def load(): T
}
