# Override fancontrol configuration file, making this SC specific
FILESEXTRAPATHS:prepend:system-controller := "${THISDIR}/${PN}:"

PACKAGE_ARCH:system-controller = "${MACHINE_ARCH}"
SRC_URI:append:system-controller = " file://sysmon.conf"

do_install:append:system-controller() {
    install -m 0644 ${WORKDIR}/sysmon.conf ${D}${sysconfdir}/sensors.d
}

# libsensors configuration file
FILES:${PN}-libsensors:append:system-controller = " ${sysconfdir}/sensors.d/sysmon.conf"
