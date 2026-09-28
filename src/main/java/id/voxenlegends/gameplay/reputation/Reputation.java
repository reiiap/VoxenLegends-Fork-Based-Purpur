package id.voxenlegends.gameplay.reputation;

/** Nilai reputasi dibatasi agar perubahan content/config tidak menyebabkan overflow. */
public final class Reputation {
    public static final int MINIMUM = -100_000;
    public static final int MAXIMUM = 100_000;
    private int value;
    public synchronized int value() { return value; }
    public synchronized int change(int delta) {
        long candidate = (long) value + delta;
        value = (int) Math.max(MINIMUM, Math.min(MAXIMUM, candidate));
        return value;
    }
}
