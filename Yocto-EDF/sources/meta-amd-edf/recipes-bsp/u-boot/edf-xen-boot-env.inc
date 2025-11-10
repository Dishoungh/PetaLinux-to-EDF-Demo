# Adjust the variables for edf xen boot

SRC_URI += "file://edf-xen-boot.cmd"

inherit xen-launch-config

# Image Load Address for Xen Dom0 boot
XEN_KERNEL_LOAD_ADDRESS ??= "0xE00000"
XEN_LOAD_ADDRESS ??= "0xBA00000"
XEN_DEVICETREE_LOAD_ADDRESS ??= "0xC000000"
XEN_RAMDISK_LOAD_ADDRESS ??= "0x2600000"

# If dom0-ramdisk is used for Xen boot then set RAMDISK image to cpio.gz
KERNEL_IMAGETYPE ?= "Image"
XEN_IMAGETYPE ?= "xen"
RAMDISK_IMAGETYPE ?= "rootfs.cpio.gz"

# Set SDBOOTDEV
SDBOOTDEV ?= "0"

# Default to boot partition number where xen and xen_boot.scr files are stored in SD/eMMC partition
BOOTPARTNUM ?= "2"

# Default to booting with the xen rootfs device being partition 4 for SD/eMMC
ROOTPARTNUM ?= "4"

# Set Kernel root filesystem parameter for SD/eMMC boot
# Bootdev will automatically be set to 'sda' or 'mmcblkXp'
KERNEL_ROOT_SD ?= "root=/dev/\${bootdev}${ROOTPARTNUM} ro rootwait"

# Set Kernel root filesystem parameter for using Xen RAMDISK boot
KERNEL_ROOT_RAMDISK ?= "root=/dev/ram0 rw"

# Set the amount of memory for dom0 depending on total available memory size(DDR).
DOM0_MEM ?= "1500M"

# Specify which UART console Xen should use. You can sepecify the devicetree
# alias or full path to a node in the devicetree
# XEN_SERIAL_CONSOLES ?= "serial0" or XEN_SERIAL_CONSOLES ?= "/axi/serial@ff000000"
XEN_SERIAL_CONSOLES ?= "serial0"

# Specify additional Xen paramaters which will be appended to xen-bootargs
# This can alos be used for debugging purposes.
# Examples: XEN_CMDLINE_APPEND ?= "sched=credit loglvl=all guest_loglvl=debug"
XEN_CMDLINE_APPEND ?= ""

# Specify the max number of vcpus for dom0
# Example usage: DOM0_MAX_VCPUS = "2" or DOM0_MAX_VCPUS = "2-4"
DOM0_MAX_VCPUS ?= "1"
