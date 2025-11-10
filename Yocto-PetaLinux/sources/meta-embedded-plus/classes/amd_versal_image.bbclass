#
# Copyright (C) 2023, Advanced Micro Devices, Inc.  All rights reserved.
#
# SPDX-License-Identifier: MIT
#

# 0x8000000 -> 128MB
IMAGE_SIZE ?= "0x800_0000"
OSPI_VERSION ?= ""
OSPI_IMAGE_VERSION ?= ""

# OSPI Offsets
IMAGE_FPT_OFFSET ?= "0x0"
IMAGE_FPT_BACKUP_OFFSET ?= "0x2_0000"
IMAGE_ACTIVE_OFFSET ?= "0x4_0000"
IMAGE_ACTIVE_METADATA_OFFSET ?= "0x326_0000"
IMAGE_BACKUP_OFFSET ?= "0x32A_0000"
IMAGE_BACKUP_METADATA_OFFSET ?= "0x64C_0000"
IMAGE_VERSION_OFFSET ?= "0x7FE_0000"

IMAGE_COMPONENTS ?= "fpt fpt_backup image_active active_meta image_backup backup_meta version"

def generate_image(d):

    import io

    # OSPI Offsets
    image_fpt_offset = int(d.getVar("IMAGE_FPT_OFFSET") or '0', 0)
    image_fpt_backup_offset = int(d.getVar("IMAGE_FPT_BACKUP_OFFSET") or '0', 0)
    image_active_offset = int(d.getVar("IMAGE_ACTIVE_OFFSET") or '0', 0)
    image_active_metadata_offset = int(d.getVar("IMAGE_ACTIVE_METADATA_OFFSET") or '0', 0)
    image_backup_offset = int(d.getVar("IMAGE_BACKUP_OFFSET") or '0', 0)
    image_backup_metadata_offset = int(d.getVar("IMAGE_BACKUP_METADATA_OFFSET") or '0', 0)
    image_version_offset = int(d.getVar("IMAGE_VERSION_OFFSET") or '0', 0)
    image_components = d.getVar("IMAGE_COMPONENTS").split() or ''

    # OSPI data
    image_size = int(d.getVar("IMAGE_SIZE") or '0', 0)
    image_data = io.BytesIO()
    image_data.write(b'\xFF' * image_size)

    # Flash Partition Table (FPT)
    if "fpt" in image_components:
        fpt_file = d.getVar("DEPLOY_DIR_IMAGE")+"/fpt-"+d.getVar("MACHINE")+".bin"
        try:
            with open(fpt_file, "rb") as f:
                fpt_data = f.read(-1)
        except OSError as err:
            bb.fatal("Unable to open FPT file: " + str(err))

        image_data.seek(image_fpt_offset)
        image_data.write(fpt_data)

        if "fpt_backup" in image_components:
            image_data.seek(image_fpt_backup_offset)
            image_data.write(fpt_data)

    # Image Active and Backup - boot.bin
    if "image_active" in image_components:
        try:
            with open(d.getVar("DEPLOY_DIR_IMAGE")+"/boot.bin", "rb") as f:
                bootbin = f.read(-1)
        except OSError as err:
            bb.fatal("Unable to open boot.bin file: " + str(err))

        image_data.seek(image_active_offset)
        image_data.write(bootbin)

        if "image_backup" in image_components:
            image_data.seek(image_backup_offset)
            image_data.write(bootbin)

    # Image metadata
    if "active_meta" in image_components:
        image_data.seek(image_active_metadata_offset)
        image_data.write(bytearray("PDIM", 'utf8')) # Magic string "PDIM"
        image_data.write(b'\x00\x00\x00\x00') #TODO Version
        image_data.write(len(bootbin).to_bytes(4,'little'))
        image_data.write(b'\x00\x00\x00\x00') #TODO Checksum

        if "backup_meta" in image_components:
            image_data.seek(image_backup_metadata_offset)
            image_data.write(bytearray("PDIM", 'utf8')) # Magic string "PDIM"
            image_data.write(b'\x00\x00\x00\x00') #TODO Version
            image_data.write(len(bootbin).to_bytes(4,'little'))
            image_data.write(b'\x00\x00\x00\x00') #TODO Checksum

    # OSPI Version
    if "version" in image_components:
        version = d.getVar('OSPI_IMAGE_VERSION')
        version = f"{version}\x00"

        image_data.seek(image_version_offset)
        image_data.write(version.encode())

    # Write the OSPI data to file
    with open(d.getVar("B") + "/" + d.getVar("PN") + ".bin", "wb") as f:
        f.write(image_data.getbuffer())


python amd_versal_image_do_compile() {
    generate_image(d)
}

EXPORT_FUNCTIONS do_compile
