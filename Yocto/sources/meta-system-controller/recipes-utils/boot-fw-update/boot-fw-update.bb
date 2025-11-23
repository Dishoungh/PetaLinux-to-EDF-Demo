DESCRIPTION = "Script and service to notify about BOOT.bin firmware updates."
SUMMARY = "BOOT.bin firmware update notifier."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://boot-fw-update.service \
           file://boot-fw-update.sh"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "boot-fw-update.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${WORKDIR}/boot-fw-update.sh ${D}/usr/bin/boot-fw-update.sh
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/boot-fw-update.service ${D}${systemd_system_unitdir}
}

RDEPENDS:${PN} += "bash"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:system-controller = "${MACHINE}"
