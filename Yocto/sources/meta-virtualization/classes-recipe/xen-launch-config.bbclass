# Copyright (C) 2024, Advanced Micro Devices, Inc.  All rights reserved.
#
# SPDX-License-Identifier: MIT
#
# This bbclass defines u-boot script variables required for xen boot which can be
# inherited u-boot boot scripts recipes and also allows to configure these variables
# from recipes, global and machine configurations files.

# Variable nomenclature is aligned with
# https://xenbits.xen.org/docs/unstable/misc/xen-command-line.html

# Image Load Address for Xen Dom0 boot
KERNEL_LOAD_ADDRESS ??= "0x00400000"
XEN_LOAD_ADDRESS ??= "0x00200000"
DEVICETREE_LOAD_ADDRESS ??= "0xC000000"
RAMDISK_LOAD_ADDRESS ??= "0x2600000"

# Xen boot image types.
# KERNEL_IMAGETYPE: Specifies DomU kernel image file to be loaded by u-boot.
# XEN_IMAGETYPE: Specifies xen hypervisor binary to be loaded by u-boot.
#                Example: xen or xen.efi or xen.gz
# DOM0_RAMDISK_IMAGETYPE: Specifies DOM0 ramdisk to be used, Example: cpio.gz
XEN_IMAGETYPE ??= "xen"
DOM0_RAMDISK_IMAGETYPE ??= "rootfs.cpio.gz"

# Set the amount of memory for dom0 depending on total available memory size(DDR).
DOM0_MEM ??= "256M"

# Specify which UART console Xen should use. You can sepecify the devicetree
# alias or full path to a node in the devicetree
# XEN_SERIAL_CONSOLES = "/soc/serial@7e215040" or
# XEN_SERIAL_CONSOLES = "serial0" or
# XEN_SERIAL_CONSOLES = "/axi/serial@ff000000"
XEN_SERIAL_CONSOLES ??= "/soc/serial@7e215040"

# Specify additional command line arguments used for Xen and this will be appended
# to xen-bootargs cariable. This can also be used for passing debug cmd line arguments.
# Examples: XEN_CMDLINE_APPEND ?= "sched=credit loglvl=all guest_loglvl=debug"
XEN_CMDLINE_APPEND ??= "sync_console bootscrub=0"

# Specify the max number of vcpus for dom0
# Example usage: DOM0_MAX_VCPUS = "2" or DOM0_MAX_VCPUS = "2-4"
DOM0_MAX_VCPUS ??= "1"
