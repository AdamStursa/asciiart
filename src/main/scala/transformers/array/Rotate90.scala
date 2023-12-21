package transformers.array

import scala.reflect.ClassTag

class Rotate90[T: ClassTag]() extends TwoDArrayTransformer[T] {

  override def transform(toRotate: Array[Array[T]]): Array[Array[T]] = {
    require(!toRotate.isEmpty, "Array cannot be empty")
    require(!toRotate(0).isEmpty, "Array 2nd dimensions cannot be empty")
    val height = toRotate.length
    val width = toRotate(0).length
    toRotate.foreach(
      el =>
        require(
          el.length == width,
          "Array 2nd dimensions must all have same length"))

    val result = Array.ofDim[T](width, height)

    for (x <- 0 until height)
      for (y <- 0 until width)
        result(y)(height - 1 - x) = toRotate(x)(y)

    result
  }
}

object Rotate90 {

  def apply[T: ClassTag](toRotate: Array[Array[T]]): Array[Array[T]] =
    new Rotate90().transform(toRotate)
}
