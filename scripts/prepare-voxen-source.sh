#!/usr/bin/env bash
# Menyiapkan checkout Purpur aktual dengan patch Voxen yang telah direview.
set -euo pipefail
version=${1:?"Gunakan: ./scripts/prepare-voxen-source.sh <versi-minecraft>"}
if [[ "$version" != "1.21" ]]; then
  echo "Bootstrap ini hanya mendukung Minecraft 1.21; menerima: $version." >&2
  exit 2
fi
root=$(cd "$(dirname "$0")/.." && pwd)
source="$root/upstream/$version"
common="$root/patches/common"
version_patches="$root/patches/versions/$version"

if [[ ! -d "$source/.git" ]]; then
  echo "BUILD STOPPED: source Purpur 1.21 tidak tersedia di $source." >&2
  exit 1
fi
origin=$(git -C "$source" remote get-url origin 2>/dev/null || true)
if [[ "$origin" != "https://github.com/PurpurMC/Purpur.git" && "$origin" != "git@github.com:PurpurMC/Purpur.git" ]]; then
  echo "BUILD STOPPED: remote checkout bukan PurpurMC/Purpur; patch Voxen ditolak." >&2
  exit 1
fi
branch=$(git -C "$source" branch --show-current)
if [[ "$branch" != "ver/1.21" ]]; then
  echo "BUILD STOPPED: checkout harus berada pada branch Purpur ver/1.21; branch saat ini: ${branch:-detached}." >&2
  exit 1
fi
if ! git -C "$source" rev-parse --verify --quiet refs/remotes/origin/ver/1.21 >/dev/null; then
  echo "BUILD STOPPED: ref remote origin/ver/1.21 tidak ditemukan pada checkout Purpur." >&2
  exit 1
fi
mapfile -t patch_files < <(find "$common" "$version_patches" -maxdepth 1 -type f -name '*.patch' -print 2>/dev/null | sort)
if (( ${#patch_files[@]} == 0 )); then
  echo "BUILD STOPPED: patch native Voxen 1.21 belum tersedia; JAR Purpur biasa tidak akan diberi nama Voxen." >&2
  exit 1
fi
ready=0
for patch in "${patch_files[@]}"; do
  if git -C "$source" apply --check "$patch"; then
    ((ready += 1))
  elif git -C "$source" apply --reverse --check "$patch"; then
    :
  else
    echo "BUILD STOPPED: patch tidak cocok dengan source Purpur atau checkout berada pada state parsial: $patch" >&2
    exit 1
  fi
done
if (( ready != 0 && ready != ${#patch_files[@]} )); then
  echo "BUILD STOPPED: checkout berada pada state patch campuran; gunakan checkout Purpur bersih." >&2
  exit 1
fi
if (( ready == ${#patch_files[@]} )); then
  for patch in "${patch_files[@]}"; do git -C "$source" apply "$patch"; done
fi
printf 'Patch Voxen untuk Minecraft 1.21 telah diverifikasi pada checkout Purpur aktual.\n'
