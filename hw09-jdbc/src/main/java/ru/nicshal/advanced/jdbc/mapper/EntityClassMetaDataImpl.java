package ru.nicshal.advanced.jdbc.mapper;

import ru.nicshal.advanced.crm.annotations.Id;
import ru.nicshal.advanced.jdbc.exceptions.ClassMetaDataException;

import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private static final Predicate<Field> FIELD_ID_PREDICATE = field -> field.isAnnotationPresent(Id.class);
    private final Class<T> clazz;
    private final List<Field> allFields;
    private final List<Field> fieldsWithoutId;
    private final Field idField;
    private final Constructor<T> constructor;

    private EntityClassMetaDataImpl(Class<T> clazz, Field[] fields) {
        this.clazz = clazz;
        this.allFields = new LinkedList<>(Arrays.asList(fields.clone()));
        this.fieldsWithoutId = allFields
                .stream()
                .filter(FIELD_ID_PREDICATE.negate())
                .collect(Collectors.toList());
        this.idField = allFields
                .stream()
                .filter(FIELD_ID_PREDICATE)
                .findFirst()
                .orElseThrow(() -> new ClassMetaDataException("В классе не найдено поле, помеченное аннотацией @Id"));
        try {
            constructor = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new ClassMetaDataException("В классе не найден публичный конструктор", e);
        }
    }

    public static <T> EntityClassMetaData<T> of(Class<T> clazz) {
        return new EntityClassMetaDataImpl<T>(clazz, clazz.getDeclaredFields());
    }

    @Override
    public String getName() {
        return clazz.getSimpleName().toLowerCase();
    }

    @Override
    public Constructor<T> getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return this.idField;
    }

    @Override
    public List<Field> getAllFields() {
        return this.allFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return this.fieldsWithoutId;
    }

}
