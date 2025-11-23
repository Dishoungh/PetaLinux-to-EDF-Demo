FILESEXTRAPATHS:prepend:amd-cortexa72-common := "${THISDIR}/${PN}:"
FILESEXTRAPATHS:prepend:amd-cortexa78-mali-common := "${THISDIR}/${PN}:"

PACKAGE_ARCH:amd-cortexa72-common = "${MACHINE_ARCH}"
PACKAGE_ARCH:amd-cortexa78-mali-common = "${MACHINE_ARCH}"

SRC_URI:append:amd-cortexa72-common = " file://fw_env.config "
SRC_URI:append:amd-cortexa78-mali-common = " file://fw_env.config "

do_install:append:amd-cortexa72-common() {
    install -d ${D}${sysconfdir}/
    install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/
}
do_install:append:amd-cortexa78-mali-common() {
    install -d ${D}${sysconfdir}/
    install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/
}
