FILESEXTRAPATHS:prepend  := "${THISDIR}/files:"

SRC_URI += "file://amd-edf.sh"

dirs755:append = " ${sysconfdir}/profile.d"

do_install:append () {
        install -m 0755 ${WORKDIR}/amd-edf.sh ${D}${sysconfdir}/profile.d/amd-edf.sh
}

