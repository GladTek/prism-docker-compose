#!/bin/sh
set -eu

SRC=/source
OUT=/output

cp "$SRC/provisioning/scripts/"*.groovy "$OUT/"

cp "$SRC/provisioning/base.yaml" "$OUT/provisioning.yaml"

if [ "${MCP_ENABLED:-false}" = "true" ]; then
  cat "$SRC/provisioning/mcp.yaml" >> "$OUT/provisioning.yaml"
fi

echo "Rendered provisioning.yaml (MCP_ENABLED=${MCP_ENABLED:-false}):"
cat "$OUT/provisioning.yaml"
