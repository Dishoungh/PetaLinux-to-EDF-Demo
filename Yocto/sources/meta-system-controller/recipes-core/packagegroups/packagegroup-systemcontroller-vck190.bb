DESCRIPTION = "Required packages for VCK190"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

VCK190_PACKAGES = " \
        systemcontroller-app-vck190 \
        "

RDEPENDS:${PN} = "${VCK190_PACKAGES}"
