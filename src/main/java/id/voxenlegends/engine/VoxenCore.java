package id.voxenlegends.engine;

import id.voxenlegends.engine.config.VoxenConfiguration;
import id.voxenlegends.engine.packet.PacketInputValidator;
import id.voxenlegends.engine.security.Decision;

/** Bootstrap fondasi ENGINE; adapter Purpur per-versi akan memanggil kelas ini, bukan sebaliknya. */
public final class VoxenCore {
    private final VoxenConfiguration configuration;
    public VoxenCore(VoxenConfiguration configuration) { this.configuration = configuration; }
    public Decision validatePayload(int byteLength) { return PacketInputValidator.payloadLength(byteLength); }
    public VoxenConfiguration configuration() { return configuration; }
}
