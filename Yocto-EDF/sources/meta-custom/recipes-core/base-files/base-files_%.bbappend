FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://custom-motd"

do_install:append() {
    install ${WORKDIR}/custom-motd ${D}${sysconfdir}/motd
}