DESCRIPTION = "Generate the partition metadata binary for Embedded Plus"
SUMMARY = "Generate the partition metadata using xclbinutil for Embedded Plus"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "^$"

DEPENDS += "xclbinutil-native"
INHIBIT_DEFAULT_DEPS = "1"

PARTMETA_FILE ?= "partition_metadata.json"

require partition-metadata_sdt.inc

inherit deploy image-artifact-names

IMAGE_NAME_SUFFIX = ""

do_compile() {
    xclbinutil --add-section PARTITION_METADATA:JSON:${MACHINE}_${PARTMETA_FILE} \
        -o ${WORKDIR}/${PN}.xsabin --force
}

do_install() {
    install -Dm 0644 ${MACHINE}_${PARTMETA_FILE} ${D}/boot/${PARTMETA_FILE}
}

SYSROOT_DIRS += "/boot"
FILES:${PN} = "/boot/${PARTMETA_FILE}"

do_deploy() {
    install -d ${DEPLOYDIR}
    install -Dm 0644 ${MACHINE}_${PARTMETA_FILE} ${DEPLOYDIR}/${PARTMETA_FILE}
    install -Dm 0644 ${WORKDIR}/${PN}.xsabin ${DEPLOYDIR}/${IMAGE_NAME}.xsabin
    ln -sf ${IMAGE_NAME}.xsabin ${DEPLOYDIR}/${IMAGE_LINK_NAME}.xsabin
}

addtask do_deploy after do_compile
