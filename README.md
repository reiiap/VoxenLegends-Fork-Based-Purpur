# VOXEN LEGENDS

Fork Purpur bertema **Dark Fantasy, RPG, Roleplay, dan Legends**. Pemain membentuk sejarah dunia, sementara server mempertahankan state sebagai sumber kebenaran.

> Status saat ini: **belum siap operasi**. Fondasi dan transaksi domain telah diuji, tetapi checkout/patch lifecycle Purpur, persistence SQL, dan adapter packet belum tersedia di repository awal ini.

## Versi dan build

Target Minecraft: `1.21`, `1.21.1`, `1.21.2`, `1.21.3`, `1.21.4`, dan `1.21.5`. Setiap target harus memakai checkout Purpur sendiri; build menolak membuat JAR jika sumber atau artefak upstream tidak dapat diverifikasi.

```bash
./scripts/provision-upstream.sh 1.21.5
./gradlew buildVersion -PmcVersion=1.21.5
./gradlew buildAll
```

Lihat [dokumentasi build](docs/build.md), [arsitektur](docs/arsitektur.md), dan [keamanan](docs/keamanan.md).
