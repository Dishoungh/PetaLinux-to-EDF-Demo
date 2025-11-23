DESCRIPTION = "Required packages for VEK280"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

VEK280_PACKAGES = " \
		systemcontroller-firmware-vek280-a01 \
		systemcontroller-app-vek280 \
        "

RDEPENDS:${PN} = "${VEK280_PACKAGES}"

