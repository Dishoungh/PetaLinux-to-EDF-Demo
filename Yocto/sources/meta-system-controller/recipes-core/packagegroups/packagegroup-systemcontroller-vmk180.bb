DESCRIPTION = "Required packages for VMK180"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

VMK180_PACKAGES = " \
        systemcontroller-app-vmk180 \
        "

RDEPENDS:${PN} = "${VMK180_PACKAGES}"
