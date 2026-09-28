# Pemecahan Masalah

## `BUILD STOPPED: source Purpur 1.21 tidak tersedia`

Jalankan `./scripts/provision-upstream.sh 1.21`. Jika GitHub tidak dapat diakses, periksa proxy/network atau sediakan checkout Purpur resmi yang dapat diverifikasi pada `upstream/1.21`. Jangan menaruh JAR hasil unduhan sebagai pengganti source.

## Patch tidak cocok atau state campuran

Gunakan checkout Purpur bersih pada commit yang sesuai patch. Script preparation menolak state ketika hanya sebagian patch sudah diterapkan; ini mencegah compile dari source setengah-patch.

## Java 21 tidak ditemukan

Pasang JDK 21 dan arahkan Gradle ke instalasi itu. Build server tidak boleh diam-diam menggunakan Java versi lain.
