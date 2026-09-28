# Patch Purpur Voxen 1.21

Patch dalam direktori ini diterapkan **hanya** ke checkout resmi `PurpurMC/Purpur`, branch `ver/1.21`. Build memverifikasi remote dan branch sebelum menyentuh source.

Patch bootstrap minimal wajib melakukan perubahan source Purpur nyata untuk:

1. memasang branding **Voxen Legends**;
2. memasang metadata build Voxen yang dapat diverifikasi;
3. menginisialisasi `VoxenCore` dari lifecycle server Purpur yang nyata;
4. menutup resource `VoxenCore` dari shutdown lifecycle Purpur;
5. menghasilkan artifact yang memuat kelas server Minecraft dan kelas Voxen pada build yang sama.

Simpan patch version-agnostic di `common/` dan patch yang bergantung pada internal 1.21 di `versions/1.21/`. Preparation menolak checkout tanpa patch, patch yang tidak cocok, atau state patch parsial. JAR Purpur generik tidak boleh diubah nama atau digabungkan untuk menjadi artifact Voxen.
