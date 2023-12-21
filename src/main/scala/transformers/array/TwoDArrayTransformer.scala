package transformers.array

import transformers.Transformer

trait TwoDArrayTransformer[T]
    extends Transformer[Array[Array[T]], Array[Array[T]]] {}
