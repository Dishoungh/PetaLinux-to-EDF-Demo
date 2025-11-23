# Adjust the variables for xen boot

inherit xen-launch-config

# Image offsets for Xen boot.
KERNEL_OFFSET ?= "0xE00000"
DEVICETREE_OFFSET ?= "0xC000000"
RAMDISK_OFFSET ?= "0x2600000"
XEN_OFFSET ?= "0xBA00000"
XEN_LOAD_ADDRESS ?= "${@append_baseaddr(d,d.getVar('XEN_OFFSET'))}"

# If dom0-ramdisk is used for Xen boot then set RAMDISK image to cpio.gz
XEN_IMAGE_NAME ?= "xen"
RAMDISK_IMAGE ?= "rootfs.cpio.gz"

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
