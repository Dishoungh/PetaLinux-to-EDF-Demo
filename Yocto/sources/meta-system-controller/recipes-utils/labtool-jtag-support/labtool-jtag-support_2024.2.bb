DESCRIPTION = "Labtool (hw_server, xsdb, xvc_server) support for vck190 system controller"
SUMMARY = "Labtool (hw_server, xsdb, xvc_server) support for vck190 system controller"

LICENSE = "Proprietary & MIT"
LIC_FILES_CHKSUM = "file://license/LICENSE_PBO;md5=fb790ca133353ea709bb11d2d33db8b3 \
                    file://license/LICENSE_TCL;md5=ddd26d895decd0fa868c3489ddad3251 \
                    file://license/LICENSE_3RD_PARTY_HW_SERVER;md5=4650e7d6ac72ca39a349ccad766aa676 \
                    file://license/LICENSE_3RD_PARTY_CS_SERVER;md5=38e14296063e0ca8b88c1a5149284bd6 \
"

BRANCH = "xlnx_rel_v2024.2"
SRC_URI = " \
	git://github.com/Xilinx/systemctl-labtool.git;branch=${BRANCH};protocol=https \
	file://xvc.service \
	file://hw_server.service \
	file://cs_server.service \
"
SRCREV = "2addb0dd94c7c6829b7dae1263457d44ff58c791"

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
RPROVIDES:${PN} += "/usr/local/xilinx_vitis/xsdb"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:system-controller = "${MACHINE}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_configure[noexec]="1"
do_compile[noexec]="1"

do_install () {
    install -d ${D}${libdir}/
    install -d ${D}${prefix}/local/
    install -d ${D}${prefix}/local/bin/
    install -d ${D}${prefix}/local/lib/
    install -d ${D}${prefix}/local/lib/tcl8.5/
    install -d ${D}${prefix}/local/xilinx_vitis/

    cp ${S}${libdir}/libtcl* ${D}${libdir}/
    cp -r ${S}${prefix}/local/lib/tcl8.5 ${D}${prefix}/local/lib/
    cp ${S}${prefix}/local/bin/* ${D}${prefix}/local/bin/
    cp -r ${S}${prefix}/local/xilinx_vitis ${D}${prefix}/local/

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
    ${prefix}/local \
    ${prefix}/local/bin \
    ${prefix}/local/lib \
    ${prefix}/local/lib/tcl8.5 \
    ${prefix}/local/xilinx_vitis ${base_libdir}/ \
    ${base_libdir}/libtcl8.5.so ${base_libdir}/libtcltcf.so \
    ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','${sysconfdir}/init.d/xsdb', '', d)} \
    "
