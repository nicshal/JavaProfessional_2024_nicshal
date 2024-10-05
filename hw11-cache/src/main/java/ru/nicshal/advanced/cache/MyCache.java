package ru.nicshal.advanced.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class MyCache<K, V> implements Cache<K, V> {

    private final List<Listener<K, V>> listenerList;
    private final Map<K, V> cache;

    public MyCache() {
        this.listenerList = new ArrayList<>();
        this.cache = new WeakHashMap<>();;
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
        for (Listener<K, V> listener : listenerList) {
            listener.notify(key, value, "Данные добавлены в кеш");
        }
    }

    @Override
    public void remove(K key) {
        V value = cache.remove(key);
        for (Listener<K, V> listener : listenerList) {
            listener.notify(key, value, "Данные удалены из кеша");
        }
    }

    @Override
    public V get(K key) {
        V value = cache.get(key);
        for (Listener<K, V> listener : listenerList) {
            listener.notify(key, value, "Данные возвращены из кеша");
        }
        return value;
    }

    @Override
    public void addListener(Listener<K, V> listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeListener(Listener<K, V> listener) {
        listenerList.remove(listener);
    }
    
}
