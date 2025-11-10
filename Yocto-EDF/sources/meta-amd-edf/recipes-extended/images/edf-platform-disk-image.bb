DESCRIPTION = "AMD Embedded Development Framework Platform image compositing multiple rootfs and other binaries to a single wic image"
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

# For the fstab we can't use SRC_URI or WORKDIR, because do_fetch is disabled in an image recipe
# and there is no way to re-enable it
EDF_IMAGE_ROOTFS = "${DEPLOY_DIR_IMAGE}/edf-image-full-cmdline${IMAGE_MACHINE_SUFFIX}${IMAGE_NAME_SUFFIX}.tar.gz"
EDF_IMAGE_ROOTFS_DIR = "${WORKDIR}/rootfs-edf-image-full-cmdline"
EDF_IMAGE_ROOTFS_FSTAB = "${LAYERBASE_amd-edf}/files/edf-disk-image/edf-image-full-cmdline-fstab"
EDF_IMAGE_ROOTFS_FSTAB[vardepsexclude] = "LAYERBASE_amd-edf"

EDF_XEN_IMAGE_ROOTFS = "${DEPLOY_DIR_IMAGE}/edf-xen-image-full-cmdline${IMAGE_MACHINE_SUFFIX}${IMAGE_NAME_SUFFIX}.tar.gz"
EDF_XEN_IMAGE_ROOTFS_DIR = "${WORKDIR}/rootfs-edf-xen-image-full-cmdline"
EDF_XEN_IMAGE_ROOTFS_FSTAB = "${LAYERBASE_amd-edf}/files/edf-disk-image/edf-xen-image-full-cmdline-fstab"
EDF_XEN_IMAGE_ROOTFS_FSTAB[vardepsexclude] = "LAYERBASE_amd-edf"

EDF_XEN_GUEST_ROOTFS = "${DEPLOY_DIR_IMAGE}/core-image-minimal${IMAGE_MACHINE_SUFFIX}${IMAGE_NAME_SUFFIX}.cpio.gz"

WICVARS:append = "\
    WORKDIR \
    EDF_IMAGE_ROOTFS_DIR \
    EDF_XEN_IMAGE_ROOTFS_DIR \
    "

WICUFSVARS:append = "\
    WORKDIR \
    EDF_IMAGE_ROOTFS_DIR \
    EDF_XEN_IMAGE_ROOTFS_DIR \
    "

DEPENDS += " \
    edf-image-full-cmdline \
    edf-xen-image-full-cmdline \
    core-image-minimal \
    "

WKS_FILES = "edf-disk-multi-rootfs.wks"

do_rootfs[depends] += " \
    core-image-minimal:do_build \
    edf-image-full-cmdline:do_build \
    edf-xen-image-full-cmdline:do_build \
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

    (
     mkdir -p ${EDF_XEN_IMAGE_ROOTFS_DIR}
     cd ${EDF_XEN_IMAGE_ROOTFS_DIR}
     tar xvpfSz ${EDF_XEN_IMAGE_ROOTFS}

     if [ -f ${EDF_XEN_IMAGE_ROOTFS_FSTAB} ]; then
        install -m 0644 ${EDF_XEN_IMAGE_ROOTFS_FSTAB} etc/fstab
     fi
    )

    # Copy xen kernel images to /boot directory
    if [ -f ${EDF_XEN_IMAGE_ROOTFS_DIR}/boot/xen ]; then
        (cd ${EDF_XEN_IMAGE_ROOTFS_DIR}/boot && find xen* -print0 | cpio --verbose --null -pdlu ${EDF_IMAGE_ROOTFS_DIR}/boot/)
    else
        bbfatal "Copying Xen files failed from location: ${EDF_XEN_IMAGE_ROOTFS_DIR}boot/"
    fi

    # Copy xen images to /root directory or xen partition
    if [ -f ${EDF_XEN_GUEST_ROOTFS} ]; then
        install -v -m 0644 ${EDF_XEN_GUEST_ROOTFS} ${EDF_XEN_IMAGE_ROOTFS_DIR}/root/rootfs.cpio.gz
    else
        bbfatal "Copying minimal rootfs for xen guest failed"
    fi
}

do_rootfs[cleandirs] += "${EDF_IMAGE_ROOTFS_DIR} ${EDF_XEN_IMAGE_ROOTFS_DIR}"
