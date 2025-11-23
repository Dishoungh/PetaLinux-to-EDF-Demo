DESCRIPTION = "Required packages for VEK385-B1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

VEK385-B1_PACKAGES = " \
		systemcontroller-app-vek385 \
		systemcontroller-firmware-vek385-b1 \
        "

RDEPENDS:${PN} = "${VEK385-B1_PACKAGES}"

