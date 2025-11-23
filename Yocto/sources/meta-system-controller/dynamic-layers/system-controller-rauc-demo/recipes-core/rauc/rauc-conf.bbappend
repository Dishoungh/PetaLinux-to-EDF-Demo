FILESEXTRAPATHS:prepend:system-controller-rauc-demo := "${THISDIR}/files:"

do_install:prepend:system-controller-rauc-demo() {
	sed -ie 's!@MACHINE@!${MACHINE}!g' ${WORKDIR}/system.conf
}

PACKAGE_ARCH:system-controller-rauc-demo = "${MACHINE_ARCH}"
