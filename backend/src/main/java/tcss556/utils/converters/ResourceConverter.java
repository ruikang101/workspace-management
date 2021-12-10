package tcss556.utils.converters;

public interface ResourceConverter<K, V> {
  /**
   * Convert the data between different format.
   *
   * @param resource source type
   * @return T target type entity.
   */
  default V convert(K resource) {
    return null;
  }

  /**
   * Covert the update request to internal entity
   *
   * @param original existing internal entity
   * @param resource external requst model
   */
  default void convertUpdate(V original, K resource) {}
}
