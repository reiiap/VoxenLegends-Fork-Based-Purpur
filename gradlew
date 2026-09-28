#!/usr/bin/env sh
# Wrapper ringan untuk lingkungan pengembangan; CI harus menyediakan Gradle 8.14.4.
set -eu
if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
fi
echo 'Gradle 8.14.4 tidak ditemukan. Instal Gradle atau gunakan lingkungan CI Voxen.' >&2
exit 1
