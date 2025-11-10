DESCRIPTION = "Embedded-Plus AMR OSPI images"
SUMMARY = "Adaptive Management Runtime(AMR) compoment"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit deploy image-artifact-names amd_versal_image

IMAGE_NAME_SUFFIX = ""

INHIBIT_DEFAULT_DEPS = "1"

OSPI_VERSION:emb-plus-ve2302-amr = "1.0.0"

OSPI_IMAGE_VERSION:emb-plus-ve2302-amr = "${PN}-${MACHINE}-v${OSPI_VERSION}${IMAGE_VERSION_SUFFIX}"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:emb-plus-ve2302-amr = "${MACHINE}"

DEPENDS += "amcfw amr-fpt virtual/boot-bin"

do_configure[noexec] = "1"
do_install[noexec] = "1"

do_compile[depends] += "amcfw:do_deploy amr-fpt:do_deploy virtual/boot-bin:do_deploy"
IMAGE_SIZE = "0x03A0_0000"
IMAGE_ACTIVE_OFFSET = "0x0008_0000"

IMAGE_COMPONENTS:emb-plus-ve2302-amr = "fpt image_active"

do_deploy() {
    install -Dm 644 ${B}/${PN}.bin ${DEPLOYDIR}/${IMAGE_NAME}.bin
    ln -s ${IMAGE_NAME}.bin ${DEPLOYDIR}/${IMAGE_LINK_NAME}.bin
    ln -s ${IMAGE_NAME}.bin ${DEPLOYDIR}/${OSPI_IMAGE_VERSION}.bin
}

addtask do_deploy after do_compile
