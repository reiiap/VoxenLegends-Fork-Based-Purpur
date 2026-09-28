# Arsitektur Bootstrap Purpur 1.21

Build memakai checkout resmi `PurpurMC/Purpur` pada `upstream/1.21`, lalu menerapkan patch source dari `patches/common/` dan `patches/versions/1.21/`. Patch tersebut adalah satu-satunya tempat yang sah untuk menghubungkan `VoxenCore` ke lifecycle server Purpur.

Kode `src/main/java/id/voxenlegends` adalah fondasi common dan belum merupakan integrasi runtime dengan Minecraft. Build tidak menerima artifact sebelum patch native menghasilkan kelas `VoxenCore` dan kelas server Minecraft dalam JAR yang sama.
