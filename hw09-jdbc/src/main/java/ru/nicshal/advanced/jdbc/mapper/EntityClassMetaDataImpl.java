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
    private final List<Field> allFieldst;
    private final List<Field> fieldsWithoutId;
    private final Field IdField;

    private EntityClassMetaDataImpl(Class<T> clazz, Field[] fields) {
        this.clazz = clazz;
        this.allFieldst = new LinkedList<>(Arrays.asList(fields.clone()));
        this.fieldsWithoutId = allFieldst
                .stream()
                .filter(FIELD_ID_PREDICATE.negate())
                .collect(Collectors.toList());
        this.IdField = allFieldst
                .stream()
                .filter(FIELD_ID_PREDICATE)
                .findFirst()
                .orElseThrow(() -> new ClassMetaDataException("В классе не найдено поле, помеченное аннотацией @Id"));
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
        try {
            return clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new ClassMetaDataException("В классе не найден публичный конструктор", e);
        }
    }

    @Override
    public Field getIdField() {
        return this.IdField;
    }

    @Override
    public List<Field> getAllFields() {
        return this.allFieldst;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return this.fieldsWithoutId;
    }

}
