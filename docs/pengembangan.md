# Pengembangan

Setiap perubahan state harus tervalidasi server-side, atomik bila menyentuh persistence, dan memiliki regression test. Jangan melakukan modifikasi world Bukkit/Purpur dari thread asinkron tanpa jaminan thread-safety upstream.
