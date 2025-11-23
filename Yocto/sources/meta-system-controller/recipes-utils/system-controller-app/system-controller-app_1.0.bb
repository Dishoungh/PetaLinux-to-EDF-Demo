DESCRIPTION = "System Contoller App"
SUMMARY = "System Controller App"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=306deb5c0f33f4b0570c30ba8564f93f"

SC_APP_REPO = "git://github.com/Xilinx/system-controller-app.git"
SC_APP_BRANCH = "xlnx_rel_v2025.2"
SC_APP_SRCREV = "b26a4c6a8a95d9488301b84ff793a38b5db238d8"

SRC_URI = "\
    ${SC_APP_REPO};branch=${SC_APP_BRANCH};protocol=https \
    file://system_controller.service \
    file://collect_logs.sh \
    file://setup_board.sh \
    file://system_config.sh \
    file://version_info.sh \
"

SRCREV="${SC_APP_SRCREV}"

inherit systemd

SYSTEMD_PACKAGES="${PN}"
SYSTEMD_SERVICE:${PN}="system_controller.service"
SYSTEMD_AUTO_ENABLE:${PN}="enable"

S="${WORKDIR}/git"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:a2197 = "${MACHINE}"
COMPATIBLE_MACHINE:zynqmp-generic = "${MACHINE}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS += "libgpiod"
RDEPENDS:${PN} += " \
    bash \
    bootgen \
    labtool-jtag-support \
    python3-smbus2 \
    whiptail \
    netcat \
    "

do_compile(){
	cd ${S}/build/
	oe_runmake
}

do_install(){
    install -d ${D}/usr/bin/
    install -d ${D}${datadir}/system-controller-app

    cp ${S}/build/sc_app ${D}${bindir}
    cp ${S}/build/sc_appd ${D}${bindir}
    cp -r ${S}/BIT ${D}${datadir}/system-controller-app/
    cp -r ${S}/script ${D}${datadir}/system-controller-app/
    install -m 755 ${WORKDIR}/*.sh ${D}${bindir}
    install -m 755 ${S}/script/SI5518_command.py ${D}${bindir}

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/system_controller.service ${D}${systemd_system_unitdir}
}
