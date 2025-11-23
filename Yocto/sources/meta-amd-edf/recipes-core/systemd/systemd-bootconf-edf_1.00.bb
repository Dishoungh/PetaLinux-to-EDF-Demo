SUMMARY = "systemd-boot configuration files for booting with UEFI"
DESCRIPTION = "systemd-boot configuration files to be deployed to the ESP for use with EDF distro"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

RPROVIDES:${PN} += "virtual-systemd-bootconf"

FILESEXTRAPATHS:prepend:versal := "${THISDIR}/versal:"
FILESEXTRAPATHS:prepend:versal-net := "${THISDIR}/versal:"
FILESEXTRAPATHS:prepend:versal-2ve-2vm := "${THISDIR}/versal-2ve-2vm:"

inherit deploy

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:versal = "${MACHINE}"
COMPATIBLE_MACHINE:versal-net = "${MACHINE}"
COMPATIBLE_MACHINE:versal-2ve-2vm = "${MACHINE}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_DEFAULT_DEPS = "1"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

SRC_URI = "file://loader.conf"
SRC_URI:append:versal = " \
    file://edf-linux.conf \
    ${@bb.utils.contains('DISTRO_FEATURES', 'xen', 'file://edf-xen.conf', '', d)} \
    "
SRC_URI:append:versal-net = " \
    file://edf-linux.conf \
    ${@bb.utils.contains('DISTRO_FEATURES', 'xen', 'file://edf-xen.conf', '', d)} \
    "
SRC_URI:append:versal-2ve-2vm = " \
    file://edf-linux.conf \
    ${@bb.utils.contains('DISTRO_FEATURES', 'xen', 'file://edf-xen.conf', '', d)} \
    "

S = "${WORKDIR}"

do_install() {
    install -d ${D}/boot/loader/entries
    install -m 0644 ${S}/loader.conf ${D}/boot/loader/
    install -m 0644 ${S}/edf-linux.conf ${D}/boot/loader/entries/
    if [ -e "${S}/edf-xen.conf" ]; then
        install -m 0644 ${S}/edf-xen.conf ${D}/boot/loader/entries/
    fi
}

FILES:${PN} = "/boot/loader/loader.conf /boot/loader/entries/*.conf"

do_deploy() {
    install -d ${DEPLOYDIR}
    install -d ${DEPLOYDIR}/loader
    install -m 0644 ${S}/loader.conf ${DEPLOYDIR}/loader
    install -m 0644 ${S}/edf-linux.conf ${DEPLOYDIR}/loader
    if [ -e "${S}/edf-xen.conf" ]; then
        install -m 0644 ${S}/edf-xen.conf ${DEPLOYDIR}/loader
    fi
}

addtask do_deploy after do_compile
