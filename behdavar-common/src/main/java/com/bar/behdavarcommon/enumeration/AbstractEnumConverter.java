package com.bar.behdavarcommon.enumeration;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public abstract class AbstractEnumConverter<T extends Enum<T> & AbstractEnum<E> , E > implements AttributeConverter<T , E> {
    private final Class<T> clazz;

    public AbstractEnumConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E convertToDatabaseColumn(T t) {
        return t != null ? t.getValue() : null;
    }

    @Override
    public T convertToEntityAttribute(E e) {
        T[] enums = clazz.getEnumConstants();
        for(T t :enums){
            if(t.getValue().equals(e)){
                return t;
            }
        }
        throw new UnsupportedOperationException();
    }
}
