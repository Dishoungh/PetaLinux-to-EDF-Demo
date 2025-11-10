FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://issue"
SRC_URI += "file://issue.net"

do_install_basefilesissue:append() {
    install -m 0644 ${WORKDIR}/issue* ${D}${sysconfdir}
}