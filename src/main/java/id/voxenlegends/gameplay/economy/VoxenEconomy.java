package id.voxenlegends.gameplay.economy;

import id.voxenlegends.engine.transaction.OperationLedger;
import id.voxenlegends.engine.transaction.TransactionResult;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/** Buku besar minor-unit server-authoritative. Seluruh transfer diserialisasi agar debit dan kredit atomik. */
public final class VoxenEconomy {
    private final Map<UUID, Long> balances = new HashMap<>();
    private final OperationLedger ledger;
    public VoxenEconomy(OperationLedger ledger) { this.ledger = Objects.requireNonNull(ledger); }

    public synchronized long balance(UUID playerId) { return balances.getOrDefault(requirePlayer(playerId), 0L); }
    public TransactionResult deposit(UUID operationId, UUID playerId, long amount) {
        return ledger.executeOnce(operationId, () -> mutate(playerId, amount));
    }
    public TransactionResult withdraw(UUID operationId, UUID playerId, long amount) {
        if (amount <= 0) return TransactionResult.rejected("Jumlah transaksi harus lebih besar dari nol.");
        return ledger.executeOnce(operationId, () -> mutate(playerId, -amount));
    }
    public TransactionResult transfer(UUID operationId, UUID from, UUID to, long amount) {
        if (amount <= 0) return TransactionResult.rejected("Jumlah transfer harus lebih besar dari nol.");
        if (requirePlayer(from).equals(requirePlayer(to))) return TransactionResult.rejected("Pengirim dan penerima tidak boleh sama.");
        return ledger.executeOnce(operationId, () -> {
            synchronized (this) {
                long source = balance(from); long destination = balance(to);
                if (source < amount) return TransactionResult.rejected("Saldo tidak mencukupi.");
                if (destination > Long.MAX_VALUE - amount) return TransactionResult.rejected("Saldo penerima melampaui batas aman.");
                balances.put(from, source - amount); balances.put(to, destination + amount);
                return TransactionResult.success();
            }
        });
    }
    private synchronized TransactionResult mutate(UUID playerId, long delta) {
        long old = balance(playerId);
        if (delta > 0 && old > Long.MAX_VALUE - delta) return TransactionResult.rejected("Saldo melampaui batas aman.");
        if (delta < 0 && (delta == Long.MIN_VALUE || old < -delta)) return TransactionResult.rejected("Saldo tidak mencukupi.");
        balances.put(playerId, old + delta); return TransactionResult.success();
    }
    private static UUID requirePlayer(UUID playerId) { return Objects.requireNonNull(playerId, "UUID pemain wajib ada"); }
}
