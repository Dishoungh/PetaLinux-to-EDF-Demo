#
# This file is the Power Management Tool for system controller recipe.
#

SUMMARY = "Power Management Tool"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../LICENSE.md;beginline=1;endline=21;md5=17b8e1d4035e928378878301dbf1d92c"

REPO ?= "git://github.com/Xilinx/system-controller-pmtool.git;protocol=https"
BRANCH = "2.0"
SRCREV = "932ce4514fc81d67e26e02c77b387f924be1c912"

BRANCHARG = "${@['nobranch=1', 'branch=${BRANCH}'][d.getVar('BRANCH', True) != '']}"
SRC_URI = "${REPO};${BRANCHARG}"

SRC_URI = " \
    ${REPO};${BRANCHARG} \
    file://pmtoolrun.service \
    file://LICENSE.md \
    "

inherit update-rc.d systemd

INITSCRIPT_NAME = "pmtoolrun.sh"

SYSTEMD_PACKAGES="${PN}"
SYSTEMD_SERVICE:${PN}="pmtoolrun.service"
SYSTEMD_AUTO_ENABLE:${PN}="enable"

S = "${WORKDIR}/git"
PMTOOL_DIR = "${datadir}/${PN}"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:zynqmp-generic = "${MACHINE}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_configure[noexec]="1"
do_compile[noexec]="1"

RDEPENDS:${PN} += " \
	 bash \
	 python3-bokeh \
	 "

do_install() {
	install -d ${D}/${PMTOOL_DIR}
	cp -r ${S}/src/* ${D}/${PMTOOL_DIR}

	install -d ${D}${bindir}
	install -m 0755 ${S}/pmtoolrun.sh ${D}${bindir}
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/pmtoolrun.service ${D}${systemd_system_unitdir}

	if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
		install -d ${D}${sysconfdir}/init.d/
		install -m 0755 ${S}/pmtoolrun.sh ${D}${sysconfdir}/init.d/
	fi
}

FILES:${PN} += "\
	${PMTOOL_DIR} \
	"


