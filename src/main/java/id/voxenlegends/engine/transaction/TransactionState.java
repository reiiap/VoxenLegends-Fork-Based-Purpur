package id.voxenlegends.engine.transaction;

/** Siklus hidup operasi kritis; hanya COMMITTED yang menghasilkan perubahan final. */
public enum TransactionState { PREPARED, COMMITTED, ROLLED_BACK }
