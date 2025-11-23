DESCRIPTION = "Required packages for VHK158"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

VHK158_PACKAGES = " \
		systemcontroller-firmware-vhk158-a01 \
		systemcontroller-app-vhk158 \
        "

RDEPENDS:${PN} = "${VHK158_PACKAGES}"

