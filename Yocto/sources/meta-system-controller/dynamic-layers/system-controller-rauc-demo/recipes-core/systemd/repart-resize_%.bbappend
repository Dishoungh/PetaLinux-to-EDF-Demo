FILESEXTRAPATHS:prepend:system-controller-rauc-demo := "${THISDIR}/${PN}:"

inherit systemd
SYSTEMD_SERVICE:${PN}:system-controller-rauc-demo = "data.mount"

S:system-controller-rauc-demo = "${WORKDIR}"

SRC_URI:system-controller-rauc-demo = " \
	file://data.mount \
	file://25-rootfsA.conf \
	file://35-rootfsB.conf \
	file://45-data.conf \
	"

do_install:system-controller-rauc-demo() {
	install -d ${D}${sysconfdir}/repart.d/
	install -m 0644 ${WORKDIR}/25-rootfsA.conf ${D}${sysconfdir}/repart.d/
	install -m 0644 ${WORKDIR}/35-rootfsB.conf ${D}${sysconfdir}/repart.d/
	install -m 0644 ${WORKDIR}/45-data.conf ${D}${sysconfdir}/repart.d/

	install -d ${D}${sysconfdir}/systemd/system
	install -m 0644 ${WORKDIR}/data.mount ${D}${sysconfdir}/systemd/system/
}
