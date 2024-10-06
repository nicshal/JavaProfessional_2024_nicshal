package ru.nicshal.advanced.cache;

import ru.nicshal.advanced.core.repository.DataTemplateHW;
import ru.nicshal.advanced.jdbc.exceptions.ClassMetaDataException;
import ru.nicshal.advanced.jdbc.mapper.EntityClassMetaData;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DataTemplateKeyStringCache<T> implements DataTemplateHW<T>, WithListener<String, T> {

    private static final String prefix = "cache_";

    //В качестве ключа в WeakHashMap Integer, Long использовать не очень хорошо
    //Причина - часть этих объектов закеширована и реально не удаляется
    //(смотри описание типов и операции упаковки и распаковки)
    //Хорошее объяснение - смотри лекцию примерно 50-ая минута
    //Поэтому здесь используем строку с префиксом (часть строк тоже может кешироваться)
    //Здесь можно подумать какого-то другого (более эффективного) типа данных для ключа
    private final MyCache<String, T> cache;
    private final DataTemplateHW<T> dataTemplate;

    public DataTemplateKeyStringCache(DataTemplateHW<T> dataTemplate) {
        this.cache = new MyCache<>();
        this.dataTemplate = dataTemplate;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        T cacheItem = cache.get(prefix + id);
        if (cacheItem == null) {
            Optional<T> item = dataTemplate.findById(connection, id);
            item.ifPresent(t -> cache.put(prefix + id, t));
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
            cache.put(prefix + getIdValue(object), object);
        }
        return objectList;
    }

    @Override
    public long insert(Connection connection, T object) {
        long id = dataTemplate.insert(connection, object);
        setIdValue(object, id);
        cache.put(prefix + id, object);
        return id;
    }

    @Override
    public void update(Connection connection, T object) {
        dataTemplate.update(connection, object);
        cache.put(prefix + getIdValue(object), object);
    }

    @Override
    public EntityClassMetaData<T> getEntityClassMetaData() {
        return dataTemplate.getEntityClassMetaData();
    }

    @Override
    public void addListener(Listener<String, T> listener) {
        cache.addListener(listener);
    }

    @Override
    public void removeListener(Listener<String, T> listener) {
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
