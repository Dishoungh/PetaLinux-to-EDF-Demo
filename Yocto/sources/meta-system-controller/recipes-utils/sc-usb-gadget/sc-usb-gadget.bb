DESCRIPTION = "Setup SC USB gadget services for connecting to Versal"
SUMMARY = "Setup SC USB gadget services for connecting to Versal"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://sc-usb-gadget.service \
	file://usb_gadget_setup.sh \
	file://usb_gadget_remove.sh \
	file://usb_gadget_connect.sh \
	file://usb_gadget_disconnect.sh \
	file://usb_gadget_is_connected.sh \
	file://20-usb_gadget.network \
"

inherit systemd

SYSTEMD_PACKAGES="${PN}"
SYSTEMD_SERVICE:${PN}="sc-usb-gadget.service"
SYSTEMD_AUTO_ENABLE:${PN}="enable"

RDEPENDS:${PN} += " \
	gzip \
	xz \
    bash \
	"

do_install () {
    install -d ${D}${sbindir}
    install -m 0755 ${WORKDIR}/usb_gadget*.sh ${D}${sbindir}

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/sc-usb-gadget.service ${D}${systemd_system_unitdir}

    install -d ${D}${sysconfdir}/systemd/network
    install -D -m 0644 ${WORKDIR}/20-usb_gadget.network ${D}${sysconfdir}/systemd/network
}
