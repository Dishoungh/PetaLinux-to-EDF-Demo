DESCRIPTION = "Required packages for VPK120"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

VPK120_PACKAGES = " \
        systemcontroller-firmware-vpk120-b01 \
		systemcontroller-app-vpk120 \
        "

RDEPENDS:${PN} = "${VPK120_PACKAGES}"

