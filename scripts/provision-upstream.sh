#!/usr/bin/env bash
set -euo pipefail
version=${1:?"Gunakan: ./scripts/provision-upstream.sh 1.21"}
if [[ "$version" != "1.21" ]]; then
  echo "Bootstrap ini hanya mendukung Minecraft 1.21; menerima: $version." >&2
  exit 2
fi
mkdir -p upstream
if [ -e "upstream/$version" ]; then
  echo "Direktori upstream/$version sudah ada; menolak menimpa source yang mungkin belum diverifikasi." >&2
  exit 1
fi
git clone --depth 1 --branch "ver/$version" https://github.com/PurpurMC/Purpur.git "upstream/$version"
commit=$(git -C "upstream/$version" rev-parse HEAD)
printf 'Source Purpur branch ver/1.21 tersedia pada commit %s.\n' "$commit"
