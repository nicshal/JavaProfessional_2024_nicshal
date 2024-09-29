package ru.nicshal.advanced.core.repository;

import ru.nicshal.advanced.jdbc.mapper.EntityClassMetaData;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface DataTemplateDouble<T> {

    Optional<T> findById(Connection connection, long id);

    List<T> findAll(Connection connection);

    long insert(Connection connection, T object);

    void update(Connection connection, T object);

    EntityClassMetaData<T> getEntityClassMetaData();

}
