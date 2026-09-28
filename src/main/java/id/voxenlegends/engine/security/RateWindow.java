package id.voxenlegends.engine.security;

import java.util.ArrayDeque;
import java.util.Deque;

/** Pembatas laju sinkron; tidak menerima waktu yang mundur sebagai aksi valid. */
public final class RateWindow {
    private final int maximum;
    private final long windowMillis;
    private final Deque<Long> events = new ArrayDeque<>();

    public RateWindow(int maximum, long windowMillis) {
        if (maximum < 1 || windowMillis < 1) throw new IllegalArgumentException("Batas rate tidak valid");
        this.maximum = maximum;
        this.windowMillis = windowMillis;
    }

    public synchronized Decision tryAcquire(long nowMillis) {
        while (!events.isEmpty() && nowMillis - events.peekFirst() >= windowMillis) events.removeFirst();
        if (!events.isEmpty() && nowMillis < events.peekLast()) return Decision.reject("Waktu aksi tidak valid.");
        if (events.size() >= maximum) return Decision.reject("Terlalu banyak paket dalam waktu singkat.");
        events.addLast(nowMillis);
        return Decision.allow();
    }
}
