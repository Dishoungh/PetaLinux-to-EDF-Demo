FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://platform-top.h"
SRC_URI += "file://u-boot.cfg"

do_configure:append() {
    install ${WORKDIR}/platform-top.h ${S}/include/configs/
}