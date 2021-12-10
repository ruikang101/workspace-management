package tcss556.utils.converters;

public interface ResourceConverter <K, V>{
    /**
     * Convert the data between different format.
     * @param resource source type
     * @return T target type entity.
     */
    V convert(K resource);
}
