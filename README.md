# VOXEN LEGENDS

**Voxen Legends** ditujukan sebagai fork source-level dari **Purpur Minecraft 1.21 (`ver/1.21`)** dengan branding dan lifecycle Voxen native. Repository ini bukan plugin, launcher pengganti, atau JAR Purpur yang diganti nama.

## Status bootstrap

Bootstrap hanya menganggap fork siap jika source Purpur resmi, patch native Voxen, build server, dan smoke test server telah berhasil. Pada checkout ini source Purpur belum tersedia, sehingga tidak ada artifact server atau klaim integrasi runtime yang dibuat.

## Build yang reproducible

Gunakan JDK 21, lalu sediakan source resmi dan patch yang telah direview:

```bash
./scripts/provision-upstream.sh 1.21
./gradlew clean buildVersion -PmcVersion=1.21
```

Bila berhasil, artifact berada di `build/1.21/voxen-legends-1.21.jar` bersama `provenance.json`. Build akan berhenti bila source, remote, patch, atau isi artifact tidak tervalidasi. Lihat [dokumentasi build](docs/build.md) dan [pemecahan masalah](docs/troubleshooting.md).
