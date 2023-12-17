package transformers.defaults

import transformers.Transformer

class IdentityTransformer[T] extends Transformer[T, T]{
  override def transform(toTransform: T): T = toTransform
}
