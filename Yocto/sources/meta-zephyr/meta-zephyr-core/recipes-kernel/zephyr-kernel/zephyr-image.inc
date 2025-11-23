require recipes-kernel/zephyr-kernel/zephyr-kernel-src.inc
require recipes-kernel/zephyr-kernel/zephyr-kernel-common.inc
inherit deploy image-artifact-names

OECMAKE_SOURCEPATH = "${ZEPHYR_SRC_DIR}"

ZEPHYR_IMAGE_LINK_NAME ?= "${PN}-${MACHINE}"
ZEPHYR_IMAGE_BASE_NAME ?= "${PN}-${MACHINE}${IMAGE_VERSION_SUFFIX}"

do_install() {
    install -d ${D}/firmware

    install -D ${B}/zephyr/${ZEPHYR_MAKE_OUTPUT} ${D}/firmware/${ZEPHYR_IMAGE_BASE_NAME}.elf

    if [ -f ${B}/zephyr/${ZEPHYR_MAKE_BIN_OUTPUT} ]
    then
      install -D ${B}/zephyr/${ZEPHYR_MAKE_BIN_OUTPUT} ${D}/firmware/${ZEPHYR_IMAGE_BASE_NAME}.bin
    fi

    if [ -f ${B}/zephyr/${ZEPHYR_MAKE_EFI_OUTPUT} ]
    then
      install -D ${B}/zephyr/${ZEPHYR_MAKE_EFI_OUTPUT} ${D}/firmware/${ZEPHYR_IMAGE_BASE_NAME}.efi
    fi
}

FILES:${PN} = "/firmware"
INSANE_SKIP += "ldflags buildpaths"
SYSROOT_DIRS += "/firmware"

do_deploy() {
    cp ${B}/zephyr/${ZEPHYR_MAKE_OUTPUT} ${DEPLOYDIR}/${ZEPHYR_IMAGE_BASE_NAME}.elf
    ln -sf ${ZEPHYR_IMAGE_BASE_NAME}.elf ${DEPLOYDIR}/${ZEPHYR_IMAGE_LINK_NAME}.elf

    # Keep old image artifact nomenclautre for backward compatibility.
    ln -sf ${ZEPHYR_IMAGE_BASE_NAME}.elf ${DEPLOYDIR}/${PN}.elf

    if [ -f ${B}/zephyr/${ZEPHYR_MAKE_BIN_OUTPUT} ]
    then
        cp ${B}/zephyr/${ZEPHYR_MAKE_BIN_OUTPUT} ${DEPLOYDIR}/${ZEPHYR_IMAGE_BASE_NAME}.bin
        ln -sf ${ZEPHYR_IMAGE_BASE_NAME}.bin ${DEPLOYDIR}/${ZEPHYR_IMAGE_LINK_NAME}.bin

        # Keep old image artifact nomenclautre for backward compatibility.
        ln -sf ${ZEPHYR_IMAGE_BASE_NAME}.bin ${DEPLOYDIR}/${PN}.bin
    fi

    if [ -f ${B}/zephyr/${ZEPHYR_MAKE_EFI_OUTPUT} ]
    then
        cp ${B}/zephyr/${ZEPHYR_MAKE_EFI_OUTPUT} ${DEPLOYDIR}/${ZEPHYR_IMAGE_BASE_NAME}.efi
        ln -sf ${ZEPHYR_IMAGE_BASE_NAME}.efi ${DEPLOYDIR}/${ZEPHYR_IMAGE_LINK_NAME}.efi

        # Keep old image artifact nomenclautre for backward compatibility.
        ln -sf ${ZEPHYR_IMAGE_BASE_NAME}.efi ${DEPLOYDIR}/${PN}.efi
    fi

}
addtask deploy after do_install
