FILESEXTRAPATHS:prepend:system-controller := "${THISDIR}/${PN}:"

PACKAGE_ARCH:system-controller = "${MACHINE_ARCH}"
SRC_URI:append:system-controller = " file://httpd.conf "

do_install:append:system-controller() {
    install -d ${D}${sysconfdir}/apache2
    install -m 0644 ${WORKDIR}/httpd.conf ${D}${sysconfdir}/apache2
}

