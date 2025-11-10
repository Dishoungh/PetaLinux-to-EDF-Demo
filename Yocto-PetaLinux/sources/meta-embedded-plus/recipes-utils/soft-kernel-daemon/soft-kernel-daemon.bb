SUMMARY = "PS Kernel Daemon"
LICENSE = "MIT"
 
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
 
SRC_URI = " \
	file://soft-kernel-daemon.sh \
	file://soft-kernel-daemon.service \
	"
 
S = "${WORKDIR}"
 
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
 
inherit update-rc.d systemd

RDEPENDS:${PN}-essential:versal = " xrt"
 
INITSCRIPT_NAME = "soft-kernel-daemon.sh"
INITSCRIPT_PARAMS = "start 99 S ."

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "soft-kernel-daemon.service"
SYSTEMD_AUTO_ENABLE:${PN}="enable"
 
do_install() {
        if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
                install -d ${D}${sysconfdir}/init.d/
                install -m 0755 ${WORKDIR}/soft-kernel-daemon.sh ${D}${sysconfdir}/init.d/
        fi

	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/soft-kernel-daemon.sh ${D}${bindir}
        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${WORKDIR}/soft-kernel-daemon.service ${D}${systemd_system_unitdir}
}
 
FILES:${PN} += "${@bb.utils.contains('DISTRO_FEATURES','sysvinit','${sysconfdir}/*', '', d)}"
