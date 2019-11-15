package com.productionmanagement.helpers;

public class KeyValuePair<K, V> {
    public K key;
    public V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Key: %s, Value: %s", this.key.toString(), this.value.toString());
    }
}
