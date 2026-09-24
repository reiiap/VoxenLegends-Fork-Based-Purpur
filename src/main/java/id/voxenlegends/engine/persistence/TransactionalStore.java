package id.voxenlegends.engine.persistence;

import java.util.Optional;
import java.util.function.Function;

/** Kontrak persistence; implementasi harus membuat commit atomik atau tidak mengubah state. */
public interface TransactionalStore<K, V> {
    Optional<V> find(K key);
    <T> T inTransaction(Function<Transaction<K, V>, T> operation);

    interface Transaction<K, V> {
        Optional<V> find(K key);
        void insert(K key, V value);
        void replace(K key, V value);
        void remove(K key);
    }
}
