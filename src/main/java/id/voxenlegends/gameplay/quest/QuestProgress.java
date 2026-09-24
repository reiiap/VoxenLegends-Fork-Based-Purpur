package id.voxenlegends.gameplay.quest;

import id.voxenlegends.engine.transaction.OperationLedger;
import id.voxenlegends.engine.transaction.TransactionResult;
import java.util.Objects;
import java.util.UUID;

/** Completion mengubah state hanya bila aksi reward berhasil; replay operation tidak menggandakan reward. */
public final class QuestProgress {
    private QuestState state = QuestState.AVAILABLE;
    public synchronized QuestState state() { return state; }
    public synchronized boolean start() { if (state != QuestState.AVAILABLE) return false; state = QuestState.ACTIVE; return true; }
    public TransactionResult complete(UUID operationId, OperationLedger ledger, Reward reward) {
        return ledger.executeOnce(operationId, () -> {
            synchronized (this) {
                if (state != QuestState.ACTIVE) return TransactionResult.rejected("Quest tidak dalam keadaan aktif.");
                TransactionResult rewardResult = reward.grant();
                if (!rewardResult.committed()) return rewardResult;
                state = QuestState.COMPLETED;
                return TransactionResult.success();
            }
        });
    }
    @FunctionalInterface public interface Reward { TransactionResult grant(); }
}
