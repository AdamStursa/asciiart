package transformers.array

import scala.reflect.ClassTag

class FlipY[T: ClassTag] extends TwoDArrayTransformer[T] {

  override def transform(toFlip: Array[Array[T]]): Array[Array[T]] =
    toFlip.map(row => row.reverse)
}

object FlipY {
  def apply[T: ClassTag](toRotate: Array[Array[T]]): Array[Array[T]] =
    new FlipY().transform(toRotate)
}
