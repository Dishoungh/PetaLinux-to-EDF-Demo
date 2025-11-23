IMAGE_FSTYPES += "wic wic.xz"
IMAGE_FSTYPES:append:system-controller-rauc-demo = " ext4"

WKS_FILES = "system-controller-nobootpartition.wks"

UBOOT_BOOT_SCRIPT = "u-boot-edf-sc-scr"

ROOTFS_POSTPROCESS_COMMAND += "create_boot_symlink;"

create_boot_symlink () {
    ln -sf bootsc.scr ${IMAGE_ROOTFS}/boot/boot.scr
}
