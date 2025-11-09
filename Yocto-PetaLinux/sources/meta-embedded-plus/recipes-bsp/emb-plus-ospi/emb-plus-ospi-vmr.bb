DESCRIPTION = "Generate an OSPI image for the Embedded Plus platform"
SUMMARY = "OSPI image includes FPT and A/B images"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "virtual/boot-bin vmr-fpt xclbinutil-native partition-metadata"

inherit deploy image-artifact-names amd_versal_image

IMAGE_NAME_SUFFIX = ""

OSPI_VERSION:emb-plus-ve2302-xrt = "1.0"

OSPI_IMAGE_VERSION:emb-plus-ve2302-xrt = "${PN}-${MACHINE}-v${OSPI_VERSION}${IMAGE_VERSION_SUFFIX}"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:emb-plus-ve2302-xrt = "${MACHINE}"

do_configure[noexec] = "1"
do_install[noexec] = "1"

do_compile[depends] += "virtual/boot-bin:do_deploy vmr-fpt:do_deploy"

do_xsabin[depends] += "partition-metadata:do_deploy"

do_xsabin () {
    xclbinutil --force --input ${DEPLOY_DIR_IMAGE}/partition-metadata-${MACHINE}.xsabin \
        --add-section PDI:RAW:${B}/${PN}.bin --output ${B}/${PN}.xsabin
}

do_deploy () {
    install -Dm 644 ${B}/${PN}.bin ${DEPLOYDIR}/${IMAGE_NAME}.bin
    ln -s ${IMAGE_NAME}.bin ${DEPLOYDIR}/${IMAGE_LINK_NAME}.bin
    ln -s ${IMAGE_NAME}.bin ${DEPLOYDIR}/${OSPI_IMAGE_VERSION}.bin

    install -Dm 644 ${B}/${PN}.xsabin ${DEPLOYDIR}/${IMAGE_NAME}.xsabin
    ln -s ${IMAGE_NAME}.xsabin ${DEPLOYDIR}/${IMAGE_LINK_NAME}.xsabin
    ln -s ${IMAGE_NAME}.bin ${DEPLOYDIR}/${OSPI_IMAGE_VERSION}.xsabin
}

addtask xsabin after do_compile
addtask deploy after do_xsabin
