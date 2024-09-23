package ru.nicshal.advanced.jdbc.mapper;

import ru.nicshal.advanced.jdbc.exceptions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetMapper {

    private ResultSetMapper() {
    }

    public static <T> T resultSetToInstanceMapper(ResultSet resultSet, EntityClassMetaData<T> entityClassMetaData) {
        T instance = getInstance(entityClassMetaData);
        try {
            if (!resultSet.isClosed()) {
                entityClassMetaData.getAllFields().forEach(field -> fillInstanceField(instance, field, resultSet)
                );
            }
        } catch (SQLException ex) {
            throw new JdbcException("Ошибка при обработке выборки из базы данных", ex);
        }
        return instance;
    }

    private static <T> T getInstance(EntityClassMetaData<T> entityClassMetaData) {
        try {
            return entityClassMetaData.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new ClassMetaDataException("Ошибка при создании нового объекта", ex);
        }
    }

    private static <T> void fillInstanceField(T instance, Field field, ResultSet resultSet) {
        final String fieldName = field.getName();
        try {
            field.setAccessible(true);
            field.set(instance, resultSet.getObject(fieldName));
            field.setAccessible(false);
        } catch (IllegalAccessException | SQLException ex) {
            throw new JdbcException("Ошибка при заполнении поля объекта", ex);
        }
    }

}
