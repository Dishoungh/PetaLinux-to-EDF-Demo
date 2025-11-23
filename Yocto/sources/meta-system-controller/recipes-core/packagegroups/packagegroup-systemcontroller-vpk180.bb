DESCRIPTION = "Required packages for VPK180"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

VPK180_PACKAGES = " \
        systemcontroller-firmware-vpk180-a01 \
		systemcontroller-app-vpk180 \
        "

RDEPENDS:${PN} = "${VPK180_PACKAGES}"

