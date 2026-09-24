package id.voxenlegends.engine.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/** Referensi aman untuk tes: commit copy-on-write, sehingga exception tidak menghasilkan partial write. */
public final class InMemoryTransactionalStore<K, V> implements TransactionalStore<K, V> {
    private final Map<K, V> records = new HashMap<>();
    @Override public synchronized Optional<V> find(K key) { return Optional.ofNullable(records.get(key)); }
    @Override public synchronized <T> T inTransaction(Function<Transaction<K, V>, T> operation) {
        Map<K, V> staged = new HashMap<>(records);
        T result = operation.apply(new Transaction<>() {
            @Override public Optional<V> find(K key) { return Optional.ofNullable(staged.get(key)); }
            @Override public void insert(K key, V value) { if (staged.putIfAbsent(key, value) != null) throw new IllegalStateException("Rekaman sudah ada."); }
            @Override public void replace(K key, V value) { if (!staged.containsKey(key)) throw new IllegalStateException("Rekaman tidak ditemukan."); staged.put(key, value); }
            @Override public void remove(K key) { staged.remove(key); }
        });
        records.clear(); records.putAll(staged);
        return result;
    }
}
