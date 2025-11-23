FILESEXTRAPATHS:prepend:system-controller := "${THISDIR}/${PN}:"

PACKAGE_ARCH:system-controller = "${MACHINE_ARCH}"
SRC_URI:append:system-controller = " file://fw_env.config "

do_install:append:system-controller() {
    install -d ${D}${sysconfdir}/
    install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/
}
