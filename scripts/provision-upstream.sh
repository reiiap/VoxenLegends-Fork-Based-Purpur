#!/usr/bin/env bash
set -euo pipefail
version=${1:?"Gunakan: ./scripts/provision-upstream.sh <versi-minecraft>"}
case "$version" in 1.21|1.21.1|1.21.2|1.21.3|1.21.4|1.21.5) ;; *) echo "Versi Minecraft tidak didukung: $version" >&2; exit 2;; esac
mkdir -p upstream
if [ -e "upstream/$version" ]; then echo "Direktori upstream/$version sudah ada; menolak menimpa sumber yang mungkin belum diverifikasi." >&2; exit 1; fi
git clone --depth 1 --branch "$version" https://github.com/PurpurMC/Purpur.git "upstream/$version"
printf 'Sumber Purpur %s telah diambil. Periksa commit dengan: git -C upstream/%s rev-parse HEAD\n' "$version" "$version"
