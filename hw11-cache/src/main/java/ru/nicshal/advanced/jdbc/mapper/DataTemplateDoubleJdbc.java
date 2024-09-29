package ru.nicshal.advanced.jdbc.mapper;

import ru.nicshal.advanced.core.repository.DataTemplateDouble;
import ru.nicshal.advanced.core.repository.executor.DbExecutor;
import ru.nicshal.advanced.jdbc.exceptions.ClassMetaDataException;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DataTemplateDoubleJdbc<T> implements DataTemplateDouble<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateDoubleJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        final String sqlStatement = entitySQLMetaData.getSelectByIdSql();
        final Function<ResultSet, T> rsHandler =
                resultSet -> ResultSetMapper.resultSetToInstanceMapper(resultSet, entityClassMetaData);
        return dbExecutor.executeSelect(connection, sqlStatement, Collections.singletonList(id), rsHandler);
    }

    @Override
    public List<T> findAll(Connection connection) {
        final String sqlStatement = entitySQLMetaData.getSelectAllSql();
        final Function<ResultSet, T> rsHandler =
                resultSet -> ResultSetMapper.resultSetToInstanceMapper(resultSet, entityClassMetaData);
        return dbExecutor.executeSelectList(connection, sqlStatement, rsHandler);
    }

    @Override
    public long insert(Connection connection, T object) {
        final String sqlStatement = entitySQLMetaData.getInsertSql();
        return dbExecutor.executeStatement(connection, sqlStatement, getObjectFieldValues(object));
    }

    @Override
    public void update(Connection connection, T object) {
        final String sqlStatement = entitySQLMetaData.getUpdateSql();
        dbExecutor.executeStatement(connection, sqlStatement, getObjectFieldValues(object));
    }

    public EntityClassMetaData<T> getEntityClassMetaData() {
        return entityClassMetaData;
    }

    private List<Object> getObjectFieldValues(T object) {
        final List<Object> paramList = new LinkedList<>();
        for (Field field : entityClassMetaData.getFieldsWithoutId()) {
            try {
                field.setAccessible(true);
                final Object param = field.get(object);
                field.setAccessible(false);
                paramList.add(param);
            } catch (IllegalAccessException e) {
                throw new ClassMetaDataException(e);
            }
        }
        return paramList;
    }

}
