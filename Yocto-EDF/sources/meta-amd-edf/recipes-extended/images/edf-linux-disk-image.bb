DESCRIPTION = "AMD Embedded Development Framework Linux image compositing rootfs and other binaries to a single wic image"
LICENSE ?= "MIT"
PACKAGES = ""

EXCLUDE_FROM_WORLD = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_DEFAULT_DEPS = "1"

# Clear IMAGE_FEATURES, as they are implemented by the individual partition
# images and may require the core-image or other image classes
IMAGE_FEATURES = ""

# Note this recipe is NOT compatible with populate_sdk!  For an SDK, use one of the regulr image recipes.

inherit image

# By default wic is not enable in IMAGE_FSTYPES so enable WIC image type support.
IMAGE_FSTYPES = "wic wic.xz wic.bmap wic.qemu-sd"

IMAGE_FSTYPES:append:versal-2ve-2vm = " wic.ufs wic.ufs.xz wic.ufs.bmap"

# Reset the IMGCLASSES
IMGCLASSES  = "rootfs_${IMAGE_PKGTYPE} image_types ${IMAGE_CLASSES}"
IMGCLASSES += "image_types_wic image-types-xilinx-qemu"

IMGCLASSES:append:versal-2ve-2vm = " image_types_ufs"

# Clear everything else
#TOOLCHAIN_TARGET_TASK = ""
#TOOLCHAIN_TARGET_TASK_ATTEMPTONLY = ""
#POPULATE_SDK_POST_TARGET_COMMAND = ""

# Root filesystem part uuid must match in the wic and kernel command line (in ubootefi.var)
ROOTFS_PART_UUID = "3C68F6D9-0132-48EE-A739-6302E3CA6EF4"

ROOTFS_PART_TYPE = "B921B045-1DF0-41C3-AF44-4C6F280D3FAE"

# EFI System Partition (ESP) UUID must match in the wic and ubootefi.var file
ESP_PART_UUID = "BF3C203E-435A-48F6-BDFE-D729C531491B"

# ESP partition UUID is fixed - uboot will not detect the ESP partition if this is not set
ESP_PART_TYPE = "C12A7328-F81F-11D2-BA4B-00A0C93EC93B"

IMAGE_EFI_BOOT_FILES ?= ""
IMAGE_EFI_BOOT_FILES:versal-2ve-2vm = "ubootefi.var"

# For the fstab we can't use SRC_URI or WORKDIR, because do_fetch is disabled in an image recipe
# and there is no way to re-enable it
EDF_IMAGE_ROOTFS = "${DEPLOY_DIR_IMAGE}/edf-image-full-cmdline${IMAGE_MACHINE_SUFFIX}${IMAGE_NAME_SUFFIX}.tar.gz"
EDF_IMAGE_ROOTFS_DIR = "${WORKDIR}/rootfs-edf-image-full-cmdline"
EDF_IMAGE_ROOTFS_FSTAB = "${LAYERBASE_amd-edf}/files/edf-disk-image/edf-image-full-cmdline-fstab"
EDF_IMAGE_ROOTFS_FSTAB[vardepsexclude] = "LAYERBASE_amd-edf"

WICVARS:append = "\
    WORKDIR \
    EDF_IMAGE_ROOTFS_DIR \
    ROOTFS_PART_UUID \
    ROOTFS_PART_TYPE \
    ESP_PART_UUID \
    ESP_PART_TYPE \
    "

WICUFSVARS:append = "\
    WORKDIR \
    EDF_IMAGE_ROOTFS_DIR \
    ROOTFS_PART_UUID \
    ROOTFS_PART_TYPE \
    ESP_PART_UUID \
    ESP_PART_TYPE \
    "

DEPENDS += " \
    edf-image-full-cmdline \
    "

# Recipe dependency of u-boot-efi-var
EFI_DEPENDS ?= ""
EFI_DEPENDS:versal-2ve-2vm = "u-boot-efi-var"
DEPENDS += "${EFI_DEPENDS}"

# Image dependency of u-boot-efi-var:do_deploy
IMAGE_DEPENDS ?= ""
IMAGE_DEPENDS:versal-2ve-2vm = "u-boot-efi-var:do_deploy"
do_configure[depends] += "${IMAGE_DEPENDS}"

# Remove the dependency on UBOOT_BOOT_SCRIPT as it is not used with UEFI boot
IMAGE_BOOT_FILES:remove:versal-2ve-2vm = "boot.scr"

WKS_FILES = "edf-disk-single-rootfs.wks"
WKS_FILES:versal-2ve-2vm = "edf-disk-single-rootfs-efi.wks"

do_rootfs[depends] += " \
    edf-image-full-cmdline:do_build \
    "

do_rootfs[prefuncs] += "edf_check_rootfs"
fakeroot do_rootfs() {
    (
     mkdir -p ${EDF_IMAGE_ROOTFS_DIR}
     cd ${EDF_IMAGE_ROOTFS_DIR}
     tar xvpfSz ${EDF_IMAGE_ROOTFS}

     if [ -f ${EDF_IMAGE_ROOTFS_FSTAB} ]; then
        install -m 0644 ${EDF_IMAGE_ROOTFS_FSTAB} etc/fstab
     fi
    )
}

do_rootfs[cleandirs] += "${EDF_IMAGE_ROOTFS_DIR}"
