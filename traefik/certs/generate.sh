#!/bin/sh
# Regenerates the self-signed localhost certificate used by traefik.
set -eu

cd "$(dirname "$0")"

openssl req -x509 -newkey rsa:2048 -nodes \
  -keyout localhost.key -out localhost.crt \
  -days 825 \
  -subj "/CN=localhost" \
  -addext "subjectAltName=DNS:localhost,DNS:*.localhost,DNS:prism.localhost,DNS:mailpit.localhost,DNS:traefik.localhost"
