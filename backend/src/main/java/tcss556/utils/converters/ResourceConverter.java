package tcss556.utils.converters;

/**
 * Class implements this interface can converter data from one type to the other one also and
 * convert with merge to orginal entity.
 *
 * @param <K> Generic type of source entity type
 * @param <V> Generic type of target entity type
 */
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
