# Build Purpur 1.21

Bootstrap ini secara sengaja hanya membangun **Minecraft 1.21** menggunakan **JDK 21**. Input build yang diperlukan adalah checkout resmi Purpur branch `ver/1.21`, patch source Voxen 1.21 yang direview, serta dependency Gradle Purpur.

```bash
./scripts/provision-upstream.sh 1.21
# Tambahkan patch native yang sesuai ke patches/common atau patches/versions/1.21.
./gradlew clean buildVersion -PmcVersion=1.21
```

Build fail-closed bila source, remote Purpur, patch, atau artifact tidak tervalidasi. Patch preparation idempoten: seluruh patch harus belum diterapkan atau seluruhnya sudah diterapkan; state campuran ditolak.

Artifact final hanya disalin setelah build upstream menghasilkan JAR Voxen yang berisi kelas `MinecraftServer` dan `VoxenCore`. `build/1.21/provenance.json` mencatat commit Purpur, commit Voxen, Java target, dan SHA-256 artifact yang benar-benar dibangun.
