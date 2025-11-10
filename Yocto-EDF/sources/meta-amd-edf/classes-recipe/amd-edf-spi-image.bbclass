#
# Copyright (C) 2025, Advanced Micro Devices, Inc.  All rights reserved.
#
# SPDX-License-Identifier: MIT
#

INHIBIT_DEFAULT_DEPS = "1"
DEPENDS = "virtual/boot-bin uefi-capsule virtual/imgsel virtual/imgrcry gzip-native"

inherit deploy image-artifact-names
IMAGE_NAME_SUFFIX = ""

COMPATIBLE_MACHINE = "^$"

# OSPI Memory Map - 2G
IMAGE_SELECTOR_OFFSET ?= "0x0"
IMAGE_SELECTOR_BACKUP_OFFSET ?= "0x6_0000"
IMAGE_SELECTOR_SCRATCHPAD_OFFSET ?= "0xC_0000"
IMAGE_RECOVERY_OFFSET ?= "0xE_0000"
IMAGE_RECOVERY_META_OFFSET ?= "0x14E_0000"
CAPSULE_METADATA_OFFSET ?= "0x150_0000"
CAPSULE_METADATA_BACKUP_OFFSET ?= "0x152_0000"
UBOOT_ENV_OFFSET ?= "0x154_0000"
UBOOT_ENV_BACKUP_OFFSET ?= "0x156_0000"
IMAGE_A_OFFSET ?= "0x158_0000"
IMAGE_B_OFFSET ?= "0x87C_0000"
USER_SCRATCHPAD_OFFSET ?= "0xFA0_0000"
SPI_SIZE ?= "0x1000_0000"

# Default gzip settings for compressed OSPI
OSPI_GZIP_CMD ?= "gzip -f -9 -n -c --rsyncable"

# The file comes from the imgrcvry deployment recipe with a specific name
IMGRCRY_IMAGE_NAME ??= "image-recovery-${MACHINE}"
IMGRCVRY_BIN_FILE = "${DEPLOY_DIR_IMAGE}/${IMGRCRY_IMAGE_NAME}.bin"

do_compile[depends] += " \
    virtual/boot-bin:do_deploy \
    uefi-capsule:do_deploy \
    virtual/imgsel:do_deploy \
    virtual/imgrcry:do_deploy \
    "

python do_compile() {

    import io

    image_selector_offset = int(d.getVar("IMAGE_SELECTOR_OFFSET") or '0', 0)
    image_selector_backup_offset = int(d.getVar("IMAGE_SELECTOR_BACKUP_OFFSET") or '0', 0)
    image_recovery_offset = int(d.getVar("IMAGE_RECOVERY_OFFSET") or '0', 0)
    image_recovery_meta_offset = int(d.getVar("IMAGE_RECOVERY_META_OFFSET") or '0', 0)
    capsule_metadata_offset = int(d.getVar("CAPSULE_METADATA_OFFSET") or '0', 0)
    capsule_metadata_backup_offset = int(d.getVar("CAPSULE_METADATA_OFFSET") or '0', 0)
    uboot_env_offset = int(d.getVar("UBOOT_ENV_OFFSET") or '0', 0)
    uboot_env_backup_offset = int(d.getVar("UBOOT_ENV_BACKUP_OFFSET") or '0', 0)
    image_a_offset = int(d.getVar("IMAGE_A_OFFSET") or '0', 0)
    image_b_offset = int(d.getVar("IMAGE_B_OFFSET") or '0', 0)
    spi_size = int(d.getVar("SPI_SIZE") or '0', 0)

    spi_data = io.BytesIO()
    spi_data.write(b'\xFF' * spi_size)

    # Image selector

    try:
        with open(d.getVar("DEPLOY_DIR_IMAGE")+"/image-selector-"+d.getVar("MACHINE")+".bin", "rb") as f:
            imgsel_data = f.read(-1)
    except OSError as err:
        bb.fatal("Unable to open image selector file: " + str(err))

    print("INFO: Write image selector to %s\n" % image_selector_offset)
    spi_data.seek(image_selector_offset)
    spi_data.write(imgsel_data)
    print("INFO: Write image selector backup to %s\n" % image_selector_backup_offset)
    spi_data.seek(image_selector_backup_offset)
    spi_data.write(imgsel_data)

    # Image recovery

    try:
        with open(d.getVar("IMGRCVRY_BIN_FILE"), "rb") as f:
            imgrcvry_data = f.read(-1)
    except OSError as err:
        bb.fatal("Unable to open image recovery file: " + str(err))

    print("INFO: Write image recovery to %s\n" % image_recovery_offset)
    spi_data.seek(image_recovery_offset)
    spi_data.write(imgrcvry_data)

    # System ready IR - Capsule Metadata

    try:
        with open(d.getVar("DEPLOY_DIR_IMAGE")+"/uefi-capsule-"+d.getVar("MACHINE")+"-metadata.bin", "rb") as f:
            capsule_mdata = f.read(-1)
    except OSError as err:
        bb.fatal("Unable to open capsule metadata file: " + str(err))

    print("INFO: Write capsule to %s\n" % capsule_metadata_offset)
    spi_data.seek(capsule_metadata_offset)
    spi_data.write(capsule_mdata)
    print("INFO: Write capsule backup to %s\n" % capsule_metadata_backup_offset)
    spi_data.seek(capsule_metadata_backup_offset)
    spi_data.write(capsule_mdata)

    # UBoot env

    try:
        with open(d.getVar("DEPLOY_DIR_IMAGE")+"/u-boot-xlnx-initial-env.bin", "rb") as f:
            uboot_env = f.read(-1)
    except OSError as err:
        bb.fatal("Unable to open UBoot env file: " + str(err))

    # print("INFO: Write uboot env to %s\n" % uboot_env_offset)
    # spi_data.seek(uboot_env_offset)
    # spi_data.write(uboot_env)
    # print("INFO: Write uboot backup env to %s\n" % uboot_env_backup_offset)
    # spi_data.seek(uboot_env_backup_offset)
    # spi_data.write(uboot_env)

    # Image A/B

    try:
        with open(d.getVar("DEPLOY_DIR_IMAGE")+"/boot.bin", "rb") as f:
            bootbin = f.read(-1)
    except OSError as err:
        bb.fatal("Unable to open boot.bin file: " + str(err))

    #FIXME add a size check here - 116736KB max on 2GB

    print("INFO: Write image a to %s\n" % image_a_offset)
    spi_data.seek(image_a_offset)
    spi_data.write(bootbin)
    print("INFO: Write image b to %s\n" % image_b_offset)
    spi_data.seek(image_b_offset)
    spi_data.write(bootbin)

    # Write image

    with open(d.getVar("B") + "/" + d.getVar("IMAGE_NAME") + ".bin", "wb") as b:
        b.write(spi_data.getbuffer())

}

do_compress () {
    ${OSPI_GZIP_CMD} ${B}/${IMAGE_NAME}.bin > ${B}/${IMAGE_NAME}.bin.gz
}

addtask compress after do_compile

do_deploy () {
    install -Dm 644 ${B}/${IMAGE_NAME}.bin ${DEPLOYDIR}/${IMAGE_NAME}.bin
    ln -s ${IMAGE_NAME}.bin ${DEPLOYDIR}/${IMAGE_LINK_NAME}.bin
    install -Dm 644 ${B}/${IMAGE_NAME}.bin.gz ${DEPLOYDIR}/${IMAGE_NAME}.bin.gz
    ln -s ${IMAGE_NAME}.bin.gz ${DEPLOYDIR}/${IMAGE_LINK_NAME}.bin.gz
}

addtask deploy after do_compress
