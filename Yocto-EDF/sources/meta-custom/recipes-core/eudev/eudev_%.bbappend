FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://70-persistent-net.rules"

do_install:append() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/70-persistent-net.rules ${D}${sysconfdir}/udev/rules.d/
}