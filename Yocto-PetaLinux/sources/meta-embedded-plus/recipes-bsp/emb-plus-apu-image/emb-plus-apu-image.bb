DESCRIPTION = "Boot image for RAVE containing ATF, u-boot, boot.scr, Linux and rootfs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "\
    bootgen-native \
    emb-plus-image-minimal \
    u-boot-xlnx-scr \
    virtual/arm-trusted-firmware \
    virtual/bootloader \
    virtual/dtb \
    virtual/kernel \
    xclbinutil-native \
    "

inherit deploy image-artifact-names

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:emb-plus-ve2302-xrt = "emb-plus-ve2302-xrt"
COMPATIBLE_MACHINE:emb-plus-ve2302-amr = "emb-plus-ve2302-amr"

do_configure:append:emb-plus-ve2302-xrt () {
cat > ${WORKDIR}/${PN}.bif << EOF
    all:
    {
        id_code = 0x14cc8093
        extended_id_code = 0x01
        image { 
            id = 0x1c000000, name=apu_subsystem
            { core=a72-0, exception_level=el-3, trustzone, file=${DEPLOY_DIR_IMAGE}/arm-trusted-firmware.elf }
            { core=a72-0, exception_level=el-2, file=${DEPLOY_DIR_IMAGE}/u-boot.elf }
            { load=0x2000000, file=${DEPLOY_DIR_IMAGE}/emb-plus-image-minimal-${MACHINE}.rootfs.cpio.gz.u-boot }
            { load=0x20000000, file=${DEPLOY_DIR_IMAGE}/boot.scr }
            { load=0x200000, file=${DEPLOY_DIR_IMAGE}/Image }
            { load=0x1000, file=${DEPLOY_DIR_IMAGE}/system.dtb }
        }
    }
EOF
}

do_configure:append:emb-plus-ve2302-amr () {
cat > ${WORKDIR}/${PN}.bif << EOF
    all:
    {
        id_code = 0x14cc8093
        extended_id_code = 0x01
        image {
            id = 0x1c000000, name=apu_subsystem
            { core=a72-0, exception_level=el-3, trustzone, file=${DEPLOY_DIR_IMAGE}/arm-trusted-firmware.elf }
            { core=a72-0, exception_level=el-2, file=${DEPLOY_DIR_IMAGE}/u-boot.elf }
            { load=0x20800000, file=${DEPLOY_DIR_IMAGE}/emb-plus-image-minimal-${MACHINE}.rootfs.cpio.gz.u-boot }
            { load=0x20000000, file=${DEPLOY_DIR_IMAGE}/boot.scr }
            { load=0x19000000, file=${DEPLOY_DIR_IMAGE}/Image }
            { load=0x1F400000, file=${DEPLOY_DIR_IMAGE}/system.dtb }
        }
    }
EOF
}

do_compile[depends] += " \
    virtual/bootloader:do_deploy \
    virtual/arm-trusted-firmware:do_deploy \
    emb-plus-image-minimal:do_image_complete \
    u-boot-xlnx-scr:do_deploy \
    virtual/kernel:do_deploy \
    virtual/dtb:do_deploy \
    "

do_compile () {
    bootgen -image ${WORKDIR}/${PN}.bif -arch ${SOC_FAMILY} -w -o ${B}/${IMAGE_NAME}.bin
    xclbinutil --add-section PDI:RAW:${B}/${IMAGE_NAME}.bin -o ${IMAGE_NAME}.xsabin
}

do_deploy () {
    install -Dm 0644 ${B}/${IMAGE_NAME}.bin ${DEPLOYDIR}/${IMAGE_NAME}.bin
    ln -sf ${IMAGE_NAME}.bin ${DEPLOYDIR}/${PN}-${MACHINE}.bin
    install -Dm 0644 ${B}/${IMAGE_NAME}.xsabin ${DEPLOYDIR}/${IMAGE_NAME}.xsabin
    ln -sf ${IMAGE_NAME}.xsabin ${DEPLOYDIR}/${PN}-${MACHINE}.xsabin
}

addtask deploy after do_compile
