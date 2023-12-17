package transformers.array

import scala.reflect.ClassTag

class Rotate180[T : ClassTag] extends TwoDArrayTransformer[T]{

  override def transform(toRotate: Array[Array[T]]): Array[Array[T]] = {
    Rotate90[T](Rotate90[T](toRotate))
  }
}

object Rotate180{
  def apply[T : ClassTag](toRotate: Array[Array[T]]): Array[Array[T]] = new Rotate180().transform(toRotate)
}