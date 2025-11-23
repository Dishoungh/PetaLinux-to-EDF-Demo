SUMMARY = "A full featured console image for AMD Embedded Development Framework"
DESCRIPTION = "AMD Embedded Development Framework Linux image compositing rootfs and other binaries to a single wic image"

LICENSE ?= "MIT"

require edf-image-common.inc

IMAGE_FEATURES += "ssh-server-openssh package-management"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
    ${AMD-EDF_IMAGE_FULL_INSTALL} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'xen', 'packagegroup-xen', '', d)} \
    "

inherit core-image

do_rootfs[prefuncs] += "edf_check_rootfs"

# Reset the IMAGE_FSTYPES to only those we support
# cpio is NOT supported, this image will be too large for a ramdisk!
IMAGE_FSTYPES = "tar.gz wic wic.xz wic.bmap${@' wic.qemu-sd' if bb.data.inherits_class('image-types-xilinx-qemu', d) else ''}"

# Add the UFS (4k) ones when required
IMAGE_FSTYPES:append:versal-2ve-2vm = " wic.ufs wic.ufs.xz wic.ufs.bmap"

IMGCLASSES:append:versal-2ve-2vm = " image_types_ufs"

# ROOT Partition type UUID
ROOTFS_PART_TYPE = "B921B045-1DF0-41C3-AF44-4C6F280D3FAE"

# ESP partition UUID is fixed - uboot will not detect the ESP partition if this is not set
ESP_PART_TYPE = "C12A7328-F81F-11D2-BA4B-00A0C93EC93B"

IMAGE_EFI_BOOT_FILES ?= ""
IMAGE_EFI_BOOT_FILES:versal ?= " \
    loader/loader.conf;loader/loader.conf \
    loader/edf-linux.conf;loader/entries/edf-linux.conf \
    ${@bb.utils.contains('DISTRO_FEATURES', 'xen', "xen.cfg xen.efi loader/edf-xen.conf;loader/entries/edf-xen.conf", '', d)} \
    "
IMAGE_EFI_BOOT_FILES:versal-2ve-2vm ?= " \
    loader/loader.conf;loader/loader.conf \
    loader/edf-linux.conf;loader/entries/edf-linux.conf \
    ${@bb.utils.contains('DISTRO_FEATURES', 'xen', "xen.cfg xen.efi loader/edf-xen.conf;loader/entries/edf-xen.conf", '', d)} \
    "

# Generate a UUID for the rootfs and pass it to wic - we need to do it here because it also needs to
# be passed to the wic plugin which is installing the ESP config files for systemd-boot and Xen
do_rootfs_wicenv:prepend:versal () {
    import uuid
    d.setVar("ROOTFS_PART_UUID", str(uuid.uuid4()))
}
do_rootfs_wicufsenv:prepend:versal-2ve-2vm () {
    import uuid
    d.setVar("ROOTFS_PART_UUID", str(uuid.uuid4()))
}

ADDN_IMAGE_RDEPENDS = ""
ADDN_IMAGE_RDEPENDS:versal = "virtual-systemd-bootconf:do_deploy"
ADDN_IMAGE_RDEPENDS:versal-net = "virtual-systemd-bootconf:do_deploy"
ADDN_IMAGE_RDEPENDS:versal-2ve-2vm = "virtual-systemd-bootconf:do_deploy"

do_rootfs[rdepends] += "${ADDN_IMAGE_RDEPENDS}"

WICVARS:append = "\
    WORKDIR \
    ROOTFS_PART_UUID \
    ROOTFS_PART_TYPE \
    ESP_PART_TYPE \
    EFI_PROVIDER \
    "

WICUFSVARS:append = "\
    WORKDIR \
    ROOTFS_PART_UUID \
    ROOTFS_PART_TYPE \
    ESP_PART_TYPE \
    EFI_PROVIDER \
    "

WKS_FILES = "edf-disk-single-rootfs.wks"
WKS_FILES:versal = "edf-disk-single-rootfs-efi.wks"
WKS_FILES:versal-net = "edf-disk-single-rootfs-efi.wks"
WKS_FILES:versal-2ve-2vm = "edf-disk-single-rootfs-efi.wks"

# Configure QEMU boot
QB_KERNEL_ROOT:riscv32 = "/dev/vda3"
QB_KERNEL_ROOT:riscv64 = "/dev/vda3"
QB_KERNEL_ROOT:zynq = "/dev/mmcblk0p3"

VIRTUAL_SYSTEMD_BOOTCONF = ""
VIRTUAL_SYSTEMD_BOOTCONF:versal = "virtual-systemd-bootconf:do_deploy"
VIRTUAL_SYSTEMD_BOOTCONF:versal-net = "virtual-systemd-bootconf:do_deploy"
VIRTUAL_SYSTEMD_BOOTCONF:versal-2ve-2vm = "virtual-systemd-bootconf:do_deploy"

do_rootfs[rdepends] += " \
    ${VIRTUAL_SYSTEMD_BOOTCONF} \
    "
