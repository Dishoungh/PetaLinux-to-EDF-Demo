DESCRIPTION = "packETH is a Linux GUI packet generator tool for ethernet"
SECTION = "packeth"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit autotools

SRC_URI = "https://sourceforge.net/projects/packeth/files/packeth-old/packETH-1.8.tar.bz;downloadfilename=packETH-1.8.tar.bz2"
SRC_URI[sha256sum] = "0b9333640bf7c0f31190c6fa348224864bc4603519a441438085177ae4d1116e"

TARGET_CC_ARCH += "${LDFLAGS}"

S = "${WORKDIR}/packETH-${PV}"

do_configure[noexec] = '1'

do_compile () {
    ${CC} -g -O2 -Wall -Wunused -Wmissing-prototypes -Wmissing-declarations \
        ${S}/cli/cli_send.c -lm -lpthread -o packETHcli
}

do_install () {
        install -d ${D}${sbindir}
        install -m 755 ${B}/packETHcli ${D}${sbindir}/
}
