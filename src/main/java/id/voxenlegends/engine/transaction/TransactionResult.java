package id.voxenlegends.engine.transaction;

import java.util.Objects;

public record TransactionResult(TransactionState state, String reason) {
    public TransactionResult { Objects.requireNonNull(state); Objects.requireNonNull(reason); }
    public boolean committed() { return state == TransactionState.COMMITTED; }
    public static TransactionResult success() { return new TransactionResult(TransactionState.COMMITTED, ""); }
    public static TransactionResult rejected(String reason) { return new TransactionResult(TransactionState.ROLLED_BACK, reason); }
}
