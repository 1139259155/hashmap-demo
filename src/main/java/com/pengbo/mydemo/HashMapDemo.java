package com.pengbo.mydemo;

public class HashMapDemo<K, V> {

    private static final int CAPACITY = 2;
    private Entry<K, V>[] table;
    private int size;

    public void put(K k, V v) {
        if (table == null) {
            inflate();
        }

        //存入entry
        int hashCode = hash(k);
        int index = indexFor(hashCode);

        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(k)) {
                entry.key = k;
            }
        }
        addEntry(k, v, index);
    }

    //头插法插入头结点
    private void addEntry(K k, V v, int index) {
        Entry<K, V> newEntry = new Entry<K, V>(k, v, table[index]);
        table[index] = newEntry;
        size++;
    }

    private int indexFor(int hashCode) {
        return hashCode % table.length;
    }

    private int hash(K k) {
        return k.hashCode();
    }

    private void inflate() {
        table = new Entry[CAPACITY];
    }

    public V get(K k) {
        int hashCode = hash(k);
        int index = indexFor(hashCode);

        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(k)) {
                return entry.value;
            }
        }
        return null;
    }

    class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        HashMapDemo<String, String> hm = new HashMapDemo<String, String>();
        hm.put("1", "123");
        hm.put("a", "abc");
        System.out.println(hm.get("2"));

    }
}
