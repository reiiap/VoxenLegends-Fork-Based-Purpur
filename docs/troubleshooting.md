# Pemecahan Masalah

## Build versi berhenti karena sumber Purpur tidak tersedia

Ini adalah proteksi yang disengaja. Sediakan checkout Purpur untuk versi yang diminta menggunakan `./scripts/provision-upstream.sh <versi>`, periksa revisi upstream, lalu jalankan build kembali. Sistem build tidak membuat artefak pengganti dan tidak menggunakan JAR dari versi lain.

## Java 21 tidak ditemukan oleh Gradle

Pasang JDK 21 dan arahkan Gradle dengan properti `org.gradle.java.installations.paths`. Jangan membangun release dengan toolchain yang berbeda tanpa memverifikasi kompatibilitasnya.
