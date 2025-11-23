#
# This file is the scweb recipe.
#

SUMMARY = "Landing Page"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/LICENSE.md;md5=691ccffd5cbf3847f255a28754844a10"


SRC_URI = "git://github.com/Xilinx/system-controller-web.git;branch=xlnx_rel_v2025.2;protocol=https \
	   file://scwebrun.service \
                  "
SRCREV = "90613f66d14e28cdc86953cf5919dbb28a969a45"

inherit update-rc.d systemd

INITSCRIPT_NAME = "scwebrun.sh"
INITSCRIPT_PARAMS = "start 97 5 ."

SYSTEMD_PACKAGES="${PN}"
SYSTEMD_SERVICE:${PN}="scwebrun.service"
SYSTEMD_AUTO_ENABLE:${PN}="enable"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:zynqmp-generic = "${MACHINE}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_configure[noexec]="1"
do_compile[noexec]="1"

SCWEB_DIR = "${datadir}/${PN}"

RDEPENDS:${PN} += "bash \
        python3 \
        python3-flask \
        python3-flask-restful \
        python3-psutil \
	python3-websockets \
        system-controller-app \        
        lmsensors-sensors \
        freeipmi \
        embpf-bootfw-update-tool \
        "

do_install() {
    install -d ${D}/${SCWEB_DIR}
    cp -r ${S}/src/* ${D}/${SCWEB_DIR}

    install -d ${D}${bindir}
    install -m 0755 ${S}/scwebrun.sh ${D}${bindir}
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/scwebrun.service ${D}${systemd_system_unitdir}

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/init.d/
        install -m 0755 ${S}/scwebrun.sh ${D}${sysconfdir}/init.d/
    fi
}

FILES:${PN} += "${SCWEB_DIR}"
