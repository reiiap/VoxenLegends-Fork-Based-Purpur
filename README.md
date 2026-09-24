# VOXEN LEGENDS

Fork Purpur bertema **Dark Fantasy, RPG, Roleplay, dan Legends**. Pemain membentuk sejarah dunia, sementara server mempertahankan state sebagai sumber kebenaran.

> Status saat ini: **Phase 1 — Foundation**. Repository ini memulai fondasi yang aman dan arsitektur build, bukan mengklaim seluruh fitur gameplay sudah selesai.

## Versi dan build

Target Minecraft: `1.21`, `1.21.1`, `1.21.2`, `1.21.3`, `1.21.4`, dan `1.21.5`. Setiap target harus memakai checkout Purpur sendiri; build menolak membuat JAR jika sumber atau artefak upstream tidak dapat diverifikasi.

```bash
./scripts/provision-upstream.sh 1.21.5
./gradlew buildVersion -PmcVersion=1.21.5
./gradlew buildAll
```

Lihat [dokumentasi build](docs/build.md), [arsitektur](docs/arsitektur.md), dan [keamanan](docs/keamanan.md).
