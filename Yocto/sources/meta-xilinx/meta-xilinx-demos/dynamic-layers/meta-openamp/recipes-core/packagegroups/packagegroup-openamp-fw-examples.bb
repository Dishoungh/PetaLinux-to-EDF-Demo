DESCRIPTION = "OpenAMP firmware examples"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

OPENAMP_PACKAGES = " \
    openamp-fw-echo-testd-freertos \
    openamp-fw-mat-muld-freertos \
    openamp-fw-rpc-demo-freertos \
    "

RDEPENDS:${PN} = "${OPENAMP_PACKAGES}"

INSANE_SKIP:${PN} += "arch"
