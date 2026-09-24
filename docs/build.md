# Build

Gunakan **JDK 21** dan Gradle 8.14.4. `provision-upstream.sh` mengambil source Purpur untuk versi yang diminta ke direktori `upstream/<versi>`. Periksa commit checkout tersebut sebelum release.

`buildVersion` memanggil task `createReobfBundlerJar` pada checkout Purpur target lalu menggabungkan kelas ENGINE Voxen ke artefak tersebut setelah menghapus signature yang tidak lagi valid ke `build/<versi>/voxen-legends-<versi>.jar`. Tidak ada task yang mengganti nama JAR lintas versi.

`buildAll` menghentikan proses pada versi pertama yang gagal, sehingga release parsial tidak tersamar sebagai release lengkap.
