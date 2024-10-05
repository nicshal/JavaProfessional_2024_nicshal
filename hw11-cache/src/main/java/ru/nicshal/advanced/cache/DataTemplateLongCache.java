package ru.nicshal.advanced.cache;

import ru.nicshal.advanced.core.repository.DataTemplateHW;
import ru.nicshal.advanced.jdbc.exceptions.ClassMetaDataException;
import ru.nicshal.advanced.jdbc.mapper.EntityClassMetaData;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DataTemplateLongCache<T> implements DataTemplateHW<T>, WithListener<Long, T> {

    private final MyCache<Long, T> cache;
    private final DataTemplateHW<T> dataTemplate;

    public DataTemplateLongCache(DataTemplateHW<T> dataTemplate) {
        this.cache = new MyCache<>();
        this.dataTemplate = dataTemplate;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        T cacheItem = cache.get(id);
        if (cacheItem == null) {
            Optional<T> item = dataTemplate.findById(connection, id);
            item.ifPresent(t -> cache.put(id, t));
            return item;
        }
        return Optional.of(cacheItem);
    }

    @Override
    public List<T> findAll(Connection connection) {
        // здесь логика такая:
        // - кеш мог протухнуть. Из кеша ничего не показываем
        // - сразу идем в базу и тащим данные
        // - обновляем кеш
        // - отдаем данные наружу
        List<T> objectList = dataTemplate.findAll(connection);
        for (T object : objectList) {
            cache.put(getIdValue(object), object);
        }
        return objectList;
    }

    @Override
    public long insert(Connection connection, T object) {
        long id = dataTemplate.insert(connection, object);
        setIdValue(object, id);
        cache.put(id, object);
        return id;
    }

    @Override
    public void update(Connection connection, T object) {
        dataTemplate.update(connection, object);
        cache.put(getIdValue(object), object);
    }

    @Override
    public EntityClassMetaData<T> getEntityClassMetaData() {
        return dataTemplate.getEntityClassMetaData();
    }

    @Override
    public void addListener(Listener<Long, T> listener) {
        cache.addListener(listener);
    }

    @Override
    public void removeListener(Listener<Long, T> listener) {
        cache.removeListener(listener);
    }

    private long getIdValue(T object) {
        Field idField = getEntityClassMetaData().getIdField();
        try {
            idField.setAccessible(true);
            long fieldValue = (long) idField.get(object);
            idField.setAccessible(false);
            return fieldValue;
        } catch (IllegalAccessException e) {
            throw new ClassMetaDataException(e);
        }
    }

    private void setIdValue(T object, long id) {
        Field idField = getEntityClassMetaData().getIdField();
        try {
            idField.setAccessible(true);
            idField.set(object, id);
            idField.setAccessible(false);
        } catch (IllegalAccessException e) {
            throw new ClassMetaDataException(e);
        }
    }

}
