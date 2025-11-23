require ${LAYERPATH_xilinx}/recipes-kernel/linux/linux-xlnx_6.12.40-v2025.2.bb

SRC_URI:append = " file://amd_aarch64_mini_defconfig"

# Unsetting the KBUILD_DEFCONFIG so that kernel-yocto.bbclass will use the
# defconfig from SRC_URI for imgrcvry distro
KBUILD_DEFCONFIG:imgrcvry ?= ""

