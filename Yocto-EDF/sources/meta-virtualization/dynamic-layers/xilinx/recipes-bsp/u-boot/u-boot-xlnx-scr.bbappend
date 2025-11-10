# Include xen-boot-cmd.inc only if BOOTMODE = "xen" is set from configuration
# file and xen enabled in DISTRO_FEATURES.
# Note: BOOTMODE variable is defined in https://git.yoctoproject.org/meta-xilinx/tree/meta-xilinx-core/recipes-bsp/u-boot/u-boot-xlnx-scr.bb#n32
# to support multiple u-boot boot target for u-boot distro boot. See below files
# ZynqMP: https://github.com/u-boot/u-boot/blob/master/include/configs/xilinx_zynqmp.h#L160-L170
# Versal: https://github.com/u-boot/u-boot/blob/master/include/configs/xilinx_versal.h#L121-L129
# Versal Net: https://github.com/u-boot/u-boot/blob/master/include/configs/xilinx_versal_net.h#L117-L123
# for u-boot distro boot.
include ${@'xen-boot-env.inc' if d.getVar('BOOTMODE') == 'xen' and bb.utils.contains('DISTRO_FEATURES', 'xen', True, False, d) else ''}

# Include xen-boot-cmd.inc only if ENABLE_XEN_UBOOT_SCR is set from configuration
# file and xen enabled in DISTRO_FEATURES. xen-boot-cmd.inc is supports multiple
# boot modes such as JTAG and SD(ext4 and ramdisk).
ENABLE_XEN_UBOOT_SCR ?= ""
include ${@'xen-boot-cmd.inc' if d.getVar('ENABLE_XEN_UBOOT_SCR') == '1' and bb.utils.contains('DISTRO_FEATURES', 'xen', True, False, d) else ''}
