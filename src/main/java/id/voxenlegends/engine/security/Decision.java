package id.voxenlegends.engine.security;

/** Keputusan eksplisit dari validator server-authoritative. */
public record Decision(boolean accepted, String reason) {
    public static Decision allow() { return new Decision(true, ""); }
    public static Decision reject(String reason) { return new Decision(false, reason); }
}
