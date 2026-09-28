package id.voxenlegends.engine.config;

import java.util.Map;
import java.util.Objects;

/** Konfigurasi yang telah tervalidasi; nilai mentah tidak boleh dipakai langsung oleh gameplay. */
public record VoxenConfiguration(double maximumInteractionDistance, int antiCheatViolationMinimum) {
    public VoxenConfiguration {
        if (!Double.isFinite(maximumInteractionDistance) || maximumInteractionDistance <= 0 || maximumInteractionDistance > 16) {
            throw new IllegalArgumentException("Jarak interaksi maksimum tidak aman.");
        }
        if (antiCheatViolationMinimum < 1 || antiCheatViolationMinimum > 1_000) {
            throw new IllegalArgumentException("Batas pelanggaran anti-cheat tidak aman.");
        }
    }
    public static VoxenConfiguration from(Map<String, Object> values) {
        Objects.requireNonNull(values, "Konfigurasi tidak boleh kosong");
        Object distance = values.get("jarak-interaksi-maksimum");
        Object violations = values.get("anti-cheat.violation-minimum");
        if (!(distance instanceof Number d) || !(violations instanceof Number v)) {
            throw new IllegalArgumentException("Konfigurasi keamanan tidak lengkap atau tidak valid.");
        }
        return new VoxenConfiguration(d.doubleValue(), v.intValue());
    }
}
