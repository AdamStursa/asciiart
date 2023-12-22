package handlers

trait Handler[T] {

  def handle(input: T): Boolean
}
