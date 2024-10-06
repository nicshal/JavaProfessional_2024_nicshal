package ru.nicshal.advanced.cache;

public interface WithListener<K, V>  {

    void addListener(Listener<K, V> listener);

    void removeListener(Listener<K, V> listener);

}
