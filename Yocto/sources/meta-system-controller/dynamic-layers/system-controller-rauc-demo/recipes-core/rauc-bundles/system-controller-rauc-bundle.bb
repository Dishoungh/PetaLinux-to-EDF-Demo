DESCRIPTION = "RAUC bundle generator for System-Controller"

inherit bundle

RAUC_BUNDLE_COMPATIBLE = "${MACHINE}"
RAUC_BUNDLE_VERSION = "v2025.02"
RAUC_BUNDLE_DESCRIPTION = "RAUC Demo Bundle"
RAUC_BUNDLE_FORMAT = "verity"
RAUC_BUNDLE_SLOTS = "rootfs"
RAUC_SLOT_rootfs = "system-controller-image-full-cmdline"
RAUC_SLOT_rootfs[fstype] = "ext4"

RAUC_KEY_FILE = "${THISDIR}/files/development-1.key.pem"
RAUC_CERT_FILE = "${THISDIR}/files/development-1.cert.pem"

COMPATIBLE_MACHINE = "(^$)"
COMPATIBLE_MACHINE:eval-brd-sc-zynqmp = "eval-brd-sc-zynqmp"
COMPATIBLE_MACHINE:eval-brd-sc-zynqmp-sdt = "eval-brd-sc-zynqmp-sdt"
COMPATIBLE_MACHINE:amd-cortexa53-common = "amd-cortexa53-common"
