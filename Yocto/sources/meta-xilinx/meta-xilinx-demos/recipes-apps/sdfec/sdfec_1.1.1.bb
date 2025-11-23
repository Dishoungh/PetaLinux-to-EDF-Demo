#
# This file is the sdfec recipe.
#

SUMMARY = "sdfec applications"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/Xilinx/linux-examples.git;protocol=https;branch=xlnx_rel_v2025.2"
SRCREV = "e85a9637dd6da92ec9afc1838f55b3e323dbc456"

inherit features_check

REQUIRED_MACHINE_FEATURES = "rfsoc"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git/sd-fec-1.1"

DEPENDS += "libgpiod"

TARGETS_APPS ?= "sdfec-demo sdfec-interrupts sdfec-multi-ldpc-codes"

do_compile() {
	for app_name in ${TARGETS_APPS}; do
		oe_runmake -C ${S}/$app_name/files
	done
}

do_install() {
	install -d ${D}${bindir}
	for app_name in ${TARGETS_APPS}; do
		install -m 0755 ${S}/$app_name/files/$app_name ${D}${bindir}
	done
}
