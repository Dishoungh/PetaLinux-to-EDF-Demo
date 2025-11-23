SUMMARY = "configuration for systemd's repart mechanism"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend:zynqmp-generic := "${THISDIR}/${PN}:"

S = "${WORKDIR}"

SRC_URI = " \
	file://25-rootfsA.conf \
	"

COMPATIBLE_MACHINE = "$^"
COMPATIBLE_MACHINE:zynqmp-generic = ".*"

do_install() {
	install -d ${D}${sysconfdir}/repart.d/
	install -m 0644 ${WORKDIR}/25-rootfsA.conf ${D}${sysconfdir}/repart.d/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
RDEPENDS:${PN} += "systemd e2fsprogs-mke2fs"
