package id.voxenlegends.api;

import id.voxenlegends.engine.security.Decision;

/** API publik untuk integrasi keamanan tanpa mengekspos NMS. */
public interface VoxenSecurityAPI {
    Decision validateExternalPayload(int byteLength);
}
