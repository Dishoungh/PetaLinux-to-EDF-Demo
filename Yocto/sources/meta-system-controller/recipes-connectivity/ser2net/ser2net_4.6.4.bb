SUMMARY = "A serial to network proxy"
SECTION = "console/network"
HOMEPAGE = "http://sourceforge.net/projects/ser2net/"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "gensio libyaml"

#SRC_URI = "${SOURCEFORGE_MIRROR}/project/ser2net/ser2net/ser2net-${PV}.tar.gz \
#    file://ser2net.service \
#"
#
#SRC_URI[sha256sum] = "78ffee19d9b97e93ae65b5cec072da2b7b947fc484e9ccb3f535702f36f6ed19"

SRC_URI = " git://github.com/cminyard/ser2net.git;branch=master;protocol=https "
SRCREV = "4cf8b6405ac9ac1ba1489e784b5cfdfdc9a58ba8"

SRC_URI:append:zynqmp-generic = " \
    file://ser2net.yaml \
    file://default.yaml \
    file://ser2net.service \
    file://ser2net_config.sh \
"

PV = "4.6.4+git${SRCPV}"
S = "${WORKDIR}/git"

UPSTREAM_CHECK_URI = "http://sourceforge.net/projects/ser2net/files/ser2net"

inherit autotools pkgconfig systemd

SYSTEMD_PACKAGES="${PN}"
SYSTEMD_SERVICE:${PN} = "ser2net.service"
SYSTEMD_AUTO_ENABLE:${PN}="enable"
RDEPENDS:${PN} += "bash"

CONFFILES:${PN} += "${sysconfdir}/ser2net/ser2net.yaml"

do_install:append() {
    install -d ${D}${sysconfdir}/ser2net
    install -D -m 0644 ${WORKDIR}/ser2net.yaml ${D}${sysconfdir}/ser2net.yaml
    install -D -m 0644 ${WORKDIR}/default.yaml ${D}${sysconfdir}/ser2net/default.yaml
    install -D -m 0755 ${WORKDIR}/ser2net_config.sh ${D}${bindir}/ser2net_config.sh
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/ser2net.service ${D}${systemd_unitdir}/system/
        sed -i -e 's,@SBINDIR@,${sbindir},g' -e 's,@SYSCONFDIR@,${sysconfdir},g' ${D}${systemd_unitdir}/system/ser2net.service
    fi
}
