SUMMARY = "U-boot script for AMD Embedded Development Framework"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit deploy

DEPENDS = "u-boot-mkimage-native"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:amd-cortexa53-common = "${MACHINE}"

FILESEXTRAPATHS:prepend = "${THISDIR}/files:"
 
SRC_URI += " \
    file://edf-sc-boot.cmd \
    "


do_compile() {

	mkimage -A arm -T script -C none -n "Linux Boot script" -d "${WORKDIR}/edf-sc-boot.cmd" bootsc.scr
}


do_install() {
	install -d ${D}/boot
	install -m 0644 bootsc.scr ${D}/boot/
        
}

FILES:${PN} = "/boot/*"

do_deploy() {
	install -d ${DEPLOYDIR}
	install -m 0644 bootsc.scr ${DEPLOYDIR}
}

addtask do_deploy after do_compile before do_build

