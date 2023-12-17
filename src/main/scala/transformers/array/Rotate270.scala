package transformers.array

import scala.reflect.ClassTag

class Rotate270[T : ClassTag] extends TwoDArrayTransformer[T]{

  override def transform(toRotate: Array[Array[T]]): Array[Array[T]] = {
    Rotate90[T](Rotate180[T](toRotate))
  }
}

object Rotate270 {
  def apply[T : ClassTag](toRotate: Array[Array[T]]): Array[Array[T]] = new Rotate270().transform(toRotate)
}
