package tcss556.utils.converters;

public interface ResourceConverter <K, V>{
    V convert(K resource);
}
