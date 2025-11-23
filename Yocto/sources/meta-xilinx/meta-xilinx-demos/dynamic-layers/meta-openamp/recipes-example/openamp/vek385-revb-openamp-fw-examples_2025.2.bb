SUMMARY = "OpenAMP firmware examples demo payload processing"
DESCRIPTION = "OpenAMP firmware examples demo payload processing"
LICENSE = "CLOSED"

SRC_URI = "https://edf.amd.com/sswreleases/rel-v2025.2/edf_files/2025.2/11150857/external/openamp-zephyr-demo/versal-2ve-2vm-vek385-revb-sdt-seg_openamp-zephyr-demo_11150857.tar.gz"
SRC_URI[sha256sum] = "40cd4ff24f692e41badcd050ef2af2a482675a50e350d3f9cd86e4b6e891aa08"

OPENAMPFW_BOARD = "vek385-revb"
require conf/includes/openamp-fw-example.inc
