package id.voxenlegends;

import id.voxenlegends.engine.packet.PacketInputValidator;
import id.voxenlegends.engine.persistence.InMemoryTransactionalStore;
import id.voxenlegends.engine.security.RateWindow;

/** Test regression bebas framework untuk aturan fail-safe fondasi. */
public final class FoundationTestSuite {
    public static void main(String[] args) {
        expect(!PacketInputValidator.coordinate(Double.NaN).accepted(), "NaN harus ditolak");
        expect(!PacketInputValidator.coordinate(Double.POSITIVE_INFINITY).accepted(), "Infinity harus ditolak");
        expect(!PacketInputValidator.payloadLength(32_768).accepted(), "Payload besar harus ditolak");
        RateWindow limiter = new RateWindow(2, 1_000);
        expect(limiter.tryAcquire(100).accepted(), "Paket pertama harus diterima");
        expect(limiter.tryAcquire(101).accepted(), "Paket kedua harus diterima");
        expect(!limiter.tryAcquire(102).accepted(), "Flood harus ditolak");
        InMemoryTransactionalStore<String, Integer> store = new InMemoryTransactionalStore<>();
        store.inTransaction(tx -> { tx.insert("saldo", 10); return null; });
        try { store.inTransaction(tx -> { tx.replace("saldo", 20); throw new IllegalStateException("gagal"); }); }
        catch (IllegalStateException expected) { }
        expect(store.find("saldo").orElseThrow() == 10, "Transaksi gagal tidak boleh melakukan partial write");
        System.out.println("Seluruh pengujian fondasi Voxen lulus.");
    }
    private static void expect(boolean condition, String message) { if (!condition) throw new AssertionError(message); }
}
