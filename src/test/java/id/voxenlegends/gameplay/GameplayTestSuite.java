package id.voxenlegends.gameplay;

import id.voxenlegends.engine.transaction.OperationLedger;
import id.voxenlegends.engine.transaction.TransactionResult;
import id.voxenlegends.gameplay.economy.VoxenEconomy;
import id.voxenlegends.gameplay.quest.QuestProgress;
import id.voxenlegends.gameplay.quest.QuestState;
import java.util.UUID;

public final class GameplayTestSuite {
    public static void main(String[] args) {
        OperationLedger ledger = new OperationLedger();
        VoxenEconomy economy = new VoxenEconomy(ledger);
        UUID a = UUID.randomUUID(), b = UUID.randomUUID(), funding = UUID.randomUUID(), transfer = UUID.randomUUID();
        expect(economy.deposit(funding, a, 500).committed(), "Deposit valid wajib berhasil");
        expect(economy.deposit(funding, a, 500).committed(), "Replay deposit wajib idempoten");
        expect(economy.balance(a) == 500, "Replay tidak boleh menduplikasi saldo");
        expect(economy.transfer(transfer, a, b, 200).committed(), "Transfer valid wajib berhasil");
        expect(economy.transfer(transfer, a, b, 200).committed(), "Replay transfer wajib idempoten");
        expect(economy.balance(a) == 300 && economy.balance(b) == 200, "Transfer harus atomik");
        expect(!economy.withdraw(UUID.randomUUID(), a, 301).committed(), "Saldo negatif harus ditolak");
        QuestProgress quest = new QuestProgress(); quest.start(); int[] grants = {0};
        UUID completion = UUID.randomUUID();
        expect(quest.complete(completion, ledger, () -> { grants[0]++; return TransactionResult.success(); }).committed(), "Reward quest harus berhasil");
        expect(quest.complete(completion, ledger, () -> { grants[0]++; return TransactionResult.success(); }).committed(), "Replay quest harus idempoten");
        expect(grants[0] == 1 && quest.state() == QuestState.COMPLETED, "Reward quest tidak boleh ganda");
        System.out.println("Seluruh pengujian gameplay transaksional Voxen lulus.");
    }
    private static void expect(boolean condition, String reason) { if (!condition) throw new AssertionError(reason); }
}
