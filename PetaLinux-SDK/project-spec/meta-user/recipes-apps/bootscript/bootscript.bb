#
# This file is the bootscript recipe.
#

SUMMARY = "Simple bootscript application"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
  
inherit systemd

SYSTEMD_SERVICE:${PN} = "bootscript.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

S = "${WORKDIR}"

SRC_URI += "file://bootscript.service"
SRC_URI += "file://bootscript.sh"

do_install() {
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${S}/bootscript.service ${D}${systemd_system_unitdir}
	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${S}/bootscript.sh ${D}/${sysconfdir}/init.d/
}
