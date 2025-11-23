SUMMARY = "System Controller specific lmsensors configuration files"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " file://sysmon.conf \
            file://fancontrol \
"

RDEPENDS:${PN}-fancontrol = "lmsensors-fancontrol"
RDEPENDS:${PN}-dev = ""

do_install() {
    install -D -m 0644 ${WORKDIR}/sysmon.conf ${D}${sysconfdir}/sensors.d/sysmon.conf
    install -m 0644 ${WORKDIR}/fancontrol ${D}${sysconfdir}/fancontrol
}


PACKAGES =+ "\
            ${PN}-libsensors \
            ${PN}-fancontrol \
"

# libsensors configuration file
FILES:${PN}-libsensors = " ${sysconfdir}/sensors.d/sysmon.conf"


# fancontrol script configuration file
FILES:${PN}-fancontrol = " ${sysconfdir}/fancontrol"

