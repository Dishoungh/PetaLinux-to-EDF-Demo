SUMMARY = "SC specific systemd configuration files"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
    file://journald.conf \
"

S = "${WORKDIR}"

do_install() {
	install -D -m0644 ${WORKDIR}/journald.conf ${D}${systemd_unitdir}/journald.conf.d/00-${PN}.conf
}

FILES:${PN} += "${systemd_unitdir}/journald.conf.d/00-${PN}.conf"

RDEPENDS:${PN} += "systemd"
