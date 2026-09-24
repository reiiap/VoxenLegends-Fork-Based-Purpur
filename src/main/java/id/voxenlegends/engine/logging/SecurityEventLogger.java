package id.voxenlegends.engine.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/** Satu titik audit agar penolakan keamanan selalu memiliki konteks tanpa membocorkan payload. */
public final class SecurityEventLogger {
    private final Logger logger;
    public SecurityEventLogger(Logger logger) { this.logger = logger; }
    public void rejected(String category, String subject, String reason) {
        logger.log(Level.WARNING, () -> "[Voxen Security] Ditolak kategori=" + category + " subjek=" + subject + " alasan=" + reason);
    }
}
