package diploma.sportlife.converter;

public interface ConverterInterface<D, M> {

    M fromDto(D dto);

    D toDto(M model);
}
