FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://example-passnet.cfg_tmp \
    file://example-pvnet.cfg_tmp \
    file://example-simple.cfg_tmp \
    file://passthrough-example-part.dts \
    "

DEPENDS += "image-builder-native"

do_compile:append() {
    dtc -I dts -O dtb ${WORKDIR}/passthrough-example-part.dts -o ${WORKDIR}/passthrough-example-part.dtb
}

do_install:append() {
    install -d -m 0755 ${D}${sysconfdir}/xen
    install -m 0644 ${WORKDIR}/example-passnet.cfg_tmp ${D}${sysconfdir}/xen/example-passnet.cfg
    install -m 0644 ${WORKDIR}/example-pvnet.cfg_tmp ${D}${sysconfdir}/xen/example-pvnet.cfg
    install -m 0644 ${WORKDIR}/example-simple.cfg_tmp ${D}${sysconfdir}/xen/example-simple.cfg
    install -m 0644 ${WORKDIR}/passthrough-example-part.dtb ${D}${sysconfdir}/xen/passthrough-example-part.dtb
}

FILES:${PN} += " \
    ${sysconfdir}/xen/example-passnet.cfg \
    ${sysconfdir}/xen/example-pvnet.cfg \
    ${sysconfdir}/xen/example-simple.cfg \
    ${sysconfdir}/xen/passthrough-example-part.dtb \
    "
