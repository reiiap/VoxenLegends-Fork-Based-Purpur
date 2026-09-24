package id.voxenlegends.engine.transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

/** Ledger idempoten dalam satu process. Backend persistence wajib menyimpan identitas ini secara atomik. */
public final class OperationLedger {
    private final Map<UUID, TransactionResult> completed = new HashMap<>();

    public synchronized TransactionResult executeOnce(UUID operationId, Supplier<TransactionResult> operation) {
        Objects.requireNonNull(operationId, "ID operasi wajib ada");
        Objects.requireNonNull(operation, "Operasi wajib ada");
        TransactionResult prior = completed.get(operationId);
        if (prior != null) return prior;
        TransactionResult result = Objects.requireNonNull(operation.get(), "Hasil operasi wajib ada");
        if (result.committed()) completed.put(operationId, result);
        return result;
    }

    public synchronized int completedOperationCount() { return completed.size(); }
}
