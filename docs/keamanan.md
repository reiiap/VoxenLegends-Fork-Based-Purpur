# Keamanan

Voxen menerapkan prinsip **server-authoritative** dan **tolak aksi sebelum state rusak**. Fondasi ini sudah memvalidasi payload, teks, koordinat finite dan rentang dunia; memiliki rate limiter sinkron; serta kontrak persistence atomik dengan implementasi uji copy-on-write.

Integrasi packet Purpur, transaksi inventaris, ekonomi, dan anti-cheat belum selesai pada Phase 1. Sampai adapter tersebut ada, fondasi ini tidak boleh dipromosikan sebagai perlindungan penuh terhadap exploit produksi.
