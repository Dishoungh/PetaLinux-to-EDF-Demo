DEFAULTTUNE ?= "microblaze-v"
SOC_FAMILY ?= "microblaze-v"

require conf/machine/include/soc-family.inc
require soc-tune-include.inc

# Linux Configuration
KERNEL_IMAGETYPE ?= "Image"
KERNEL_IMAGETYPES += "uImage"
KEEPUIMAGE = "no"

UBOOT_ELF ?= "u-boot"
