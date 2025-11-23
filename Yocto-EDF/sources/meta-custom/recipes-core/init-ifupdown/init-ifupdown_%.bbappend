FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://interfaces"

do_install:append() {
    install -m 0644 ${WORKDIR}/interfaces ${D}${sysconfdir}/network/interfaces
}