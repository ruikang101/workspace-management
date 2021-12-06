package tcss556.utils.converters;

public abstract class AbstractResourceConverter<T, V> {

    public abstract V toInternalEntity(T request);

    public abstract T toExternalModel(V internalEntity);
}
