SUMMARY = "OSPI image for AMD Embedded Development Framework"
DESCRIPTION = "OSPI image for AMD Embedded Development Framework with image selector, image recovery, capsule metadata and boot.bin"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit amd-edf-spi-image

COMPATIBLE_MACHINE = "^$"

PACKAGE_ARCH = "${MACHINE_ARCH}"
