SUMMARY = "AMD specific quirk files for fwupd tool"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
   file://amd-mtd.quirk \
   file://xlnx-mtd.quirk \
   file://xlnx-pci.quirk \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/${datadir}/fwupd/quirks.d
    install -m 0644 ${S}/amd-mtd.quirk ${D}/${datadir}/fwupd/quirks.d
    install -m 0644 ${S}/xlnx-mtd.quirk ${D}/${datadir}/fwupd/quirks.d
    install -m 0644 ${S}/xlnx-pci.quirk ${D}/${datadir}/fwupd/quirks.d
}

FILES:${PN} = "${datadir}/fwupd/quirks.d"
