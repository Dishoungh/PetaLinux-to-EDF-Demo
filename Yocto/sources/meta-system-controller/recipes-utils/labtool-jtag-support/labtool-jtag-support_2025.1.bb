DESCRIPTION = "Labtool (hw_server, xsdb, xvc_server) support for vck190 system controller"
SUMMARY = "Labtool (hw_server, xsdb, xvc_server) support for vck190 system controller"

LICENSE = "Proprietary & MIT"
LIC_FILES_CHKSUM = "file://license/LICENSE_PBO;md5=fb790ca133353ea709bb11d2d33db8b3 \
                    file://license/LICENSE_TCL;md5=ddd26d895decd0fa868c3489ddad3251 \
                    file://license/LICENSE_3RD_PARTY_HW_SERVER;md5=4650e7d6ac72ca39a349ccad766aa676 \
                    file://license/LICENSE_3RD_PARTY_CS_SERVER;md5=38e14296063e0ca8b88c1a5149284bd6 \
"

BRANCH = "xlnx_rel_v2025.1"
SRC_URI = " \
	git://github.com/Xilinx/systemctl-labtool.git;branch=${BRANCH};protocol=https \
	file://xvc.service \
	file://hw_server.service \
	file://cs_server.service \
"
SRCREV = "11bab11b19625ea82f670c8887a55ff71bde1c6b"

inherit update-rc.d systemd

INSANE_SKIP:${PN} = "ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} = "already-stripped"

INITSCRIPT_NAME = "xsdb"
INITSCRIPT_PARAMS = "start 99 S ."

SYSTEMD_PACKAGES="${PN}"
SYSTEMD_SERVICE:${PN}="xvc.service hw_server.service cs_server.service"
SYSTEMD_AUTO_ENABLE:${PN}="enable"

S="${WORKDIR}/git"

DEPENDS += "zlib"
RDEPENDS:${PN} += "bash libxcrypt"
RPROVIDES:${PN} += "/opt/labtools/xilinx_vitis/xsdb"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:system-controller = "${MACHINE}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_configure[noexec]="1"
do_compile[noexec]="1"

do_install () {
    cp -r ${S}/opt ${D}${base_prefix}

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/init.d/
        install -m 0755 ${S}${sysconfdir}/init.d/xsdb ${D}${sysconfdir}/init.d/
    fi

    install -d ${D}${sysconfdir}/profile.d/
    install -m 0755 ${S}/etc/profile.d/xsdb-variables.sh ${D}${sysconfdir}/profile.d/xsdb-variables.sh

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/xvc.service ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/hw_server.service ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/cs_server.service ${D}${systemd_system_unitdir}
}

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${base_prefix}/opt/labtools \
    ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','${sysconfdir}/init.d/xsdb', '', d)} \
    "
