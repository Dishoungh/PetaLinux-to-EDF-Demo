SUMMARY = "U-boot script for AMD Embedded Development Framework"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit deploy

DEPENDS = "u-boot-mkimage-native"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE ?= "^$"
COMPATIBLE_MACHINE:zynq = "zynq"
COMPATIBLE_MACHINE:zynqmp = "zynqmp"
COMPATIBLE_MACHINE:versal = "versal"
COMPATIBLE_MACHINE:versal-net = "versal-net"
COMPATIBLE_MACHINE:versal-2ve-2vm = "versal-2ve-2vm"

SRC_URI = " \
    file://edf-linux-mmc-boot.cmd \
    "

SRC_URI:append:versal-2ve-2vm = " \
    file://edf-linux-ufs-boot.cmd \
    "

SRC_URI:zynq = " \
    file://edf-linux-mmc-boot.cmd.zynq \
    "

do_compile:prepend:zynq() {
    # We need to use a different file
    cp ${WORKDIR}/edf-linux-mmc-boot.cmd.zynq ${WORKDIR}/edf-linux-mmc-boot.cmd
}

do_compile() {

	mkimage -A arm -T script -C none -n "Linux Boot script" -d "${WORKDIR}/edf-linux-mmc-boot.cmd" boot.scr
}

do_compile:append:versal-2ve-2vm() {
    mkimage -A arm -T script -C none -n "Linux UFS Boot script" -d "${WORKDIR}/edf-linux-ufs-boot.cmd" boot_ufs.scr
}

do_install() {
	install -d ${D}/boot
	install -m 0644 boot.scr ${D}/boot
}

do_install:append:versal-2ve-2vm() {
	install -m 0644 boot_ufs.scr ${D}/boot
}

FILES:${PN} = "/boot/*"

do_deploy() {
	install -d ${DEPLOYDIR}
	install -m 0644 boot.scr ${DEPLOYDIR}
}

do_deploy:append:versal-2ve-2vm() {
    install -m 0644 boot_ufs.scr ${DEPLOYDIR}
}

addtask do_deploy after do_compile before do_build
