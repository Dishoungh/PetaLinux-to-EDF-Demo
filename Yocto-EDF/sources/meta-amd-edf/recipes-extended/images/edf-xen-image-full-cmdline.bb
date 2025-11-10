DESCRIPTION = "A full featured console image for AMD Embedded Development Framework Xen"

require edf-image-common.inc

IMAGE_FEATURES += "ssh-server-openssh package-management"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
    ${AMD-EDF_IMAGE_COMMON_INSTALL} \
    packagegroup-xen \    
    "

inherit core-image

do_rootfs[prefuncs] += "edf_check_rootfs"

