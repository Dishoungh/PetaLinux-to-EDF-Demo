FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Custom Config Header
SRC_URI += "file://platform-top.h"

# Custom U-Boot Diffconfig
SRC_URI += "file://u-boot.cfg"

do_configure:append() {
    install ${WORKDIR}/platform-top.h ${S}/include/configs/
}