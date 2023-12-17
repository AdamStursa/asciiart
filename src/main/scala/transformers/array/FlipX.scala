package transformers.array

import scala.reflect.ClassTag

class FlipX[T : ClassTag] extends TwoDArrayTransformer[T]{

  override def transform(toFlip: Array[Array[T]]): Array[Array[T]] = {
    toFlip.reverse
  }
}

object FlipX {
  def apply[T: ClassTag](toRotate: Array[Array[T]]): Array[Array[T]] = new FlipX[T]().transform(toRotate)
}
