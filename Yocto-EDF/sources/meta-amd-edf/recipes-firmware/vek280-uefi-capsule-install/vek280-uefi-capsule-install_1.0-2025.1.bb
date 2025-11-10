SUMMARY = "VEK280 Capsule files"
DESCRIPTION = "VEK280 Capsule files"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "https://petalinux.xilinx.com/sswreleases/rel-v2025.1/uefi-capsule/2025.1/07290114/versal-vek280-sdt-seg-ospi_2025.1-uefi-capsule.tar.gz"
SRC_URI[sha256sum] = "1a9cf1eb5c3727ff7c1de137b69d1b8883db014338eceb30921131837bf6ab72"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:amd-cortexa72-common = "${MACHINE}"

FW_DIR = "versal-vek280-sdt-seg-ospi_2025.1-uefi-capsule"

S = "${WORKDIR}"

do_install() {
        install -d ${D}/${nonarch_base_libdir}/firmware/xilinx/${PN}
        install -Dm 0644 ${S}/${FW_DIR}/uefi-capsule*.bin ${D}/${nonarch_base_libdir}/firmware/xilinx/${PN}/
        install -Dm 0644 ${S}/${FW_DIR}/uefi-capsule*.cab ${D}/${nonarch_base_libdir}/firmware/xilinx/${PN}/
}

FILES:${PN} += "${nonarch_base_libdir}/firmware/xilinx/${PN}"
