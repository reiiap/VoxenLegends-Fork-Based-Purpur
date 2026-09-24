# Arsitektur

- **ENGINE**: bootstrap, konfigurasi tervalidasi, logging audit, validasi paket, persistence dan adapter Purpur per-versi.
- **GAMEPLAY**: profile, faction, reputasi, quest, legend, ekonomi, crime, bounty, territory, dan world state.
- **CONTENT**: definisi lore, NPC, dialog, quest, faction, event, dungeon, dan boss data-driven.

Kode umum tidak bergantung pada NMS. Perbedaan Purpur/NMS ditempatkan pada adapter per-versi di checkout upstream, dengan kontrak API Voxen sebagai batasnya.
