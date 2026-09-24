package id.voxenlegends.engine.packet;

import id.voxenlegends.engine.security.Decision;

/** Validasi batas input sebelum data paket menyentuh state permainan. */
public final class PacketInputValidator {
    public static final int MAX_PAYLOAD_BYTES = 32_767;
    public static final int MAX_TEXT_CODE_POINTS = 8_192;
    private PacketInputValidator() { }

    public static Decision payloadLength(int length) {
        return length >= 0 && length <= MAX_PAYLOAD_BYTES ? Decision.allow()
            : Decision.reject("Ukuran payload paket tidak valid.");
    }

    public static Decision coordinate(double value) {
        return Double.isFinite(value) && Math.abs(value) <= 30_000_000D ? Decision.allow()
            : Decision.reject("Koordinat paket tidak valid.");
    }

    public static Decision text(String value) {
        if (value == null) return Decision.reject("Teks paket tidak boleh kosong.");
        return value.codePointCount(0, value.length()) <= MAX_TEXT_CODE_POINTS ? Decision.allow()
            : Decision.reject("Teks paket melebihi batas aman.");
    }
}
