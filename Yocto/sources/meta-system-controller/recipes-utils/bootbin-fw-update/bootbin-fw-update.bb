DESCRIPTION = "Auto BOOT.BIN Update."
SUMMARY = "BOOT.bin FW Update Status"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://bootbin-fw-update.service \
           file://bootbin-fw-update.sh \
"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "bootbin-fw-update.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${WORKDIR}/bootbin-fw-update.sh ${D}${bindir}
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/bootbin-fw-update.service ${D}${systemd_system_unitdir}
}

RDEPENDS:${PN} += "bash"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:system-controller = "${MACHINE}"
