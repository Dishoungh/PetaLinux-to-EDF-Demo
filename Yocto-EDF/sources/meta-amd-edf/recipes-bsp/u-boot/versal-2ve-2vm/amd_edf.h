#ifndef __CONFIG_EDF_VERSAL2_H
#define __CONFIG_EDF_VERSAL2_H

#include <configs/amd_versal2.h>

#define ENV_MEM_LAYOUT_SETTINGS \
	"fdt_addr_r=0x21000000\0" \
	"fdt_size_r=0x400000\0" \
	"pxefile_addr_r=0x10000000\0" \
	"kernel_addr_r=0x20200000\0" \
	"kernel_size_r=0x10000000\0" \
	"kernel_comp_addr_r=0x30000000\0" \
	"kernel_comp_size=0x3C00000\0" \
	"ramdisk_addr_r=0x22100000\0" \
	"script_size_f=0x80000\0"

/* Initial environment variables */
#ifndef CFG_EXTRA_ENV_SETTINGS
#define CFG_EXTRA_ENV_SETTINGS \
    ENV_MEM_LAYOUT_SETTINGS \
    BOOTENV
#endif

/* Define image type guid, this should match with the capsule-metadata recipe value*/
#define XILINX_BOOT_IMAGE_GUID \
	EFI_GUID(0xcb27e54d, 0x08f3a, 0x4c77, 0x8a, 0x72, \
		 0x1c, 0x76, 0xd2, 0xd4, 0xe9, 0x38)
#endif /* __CONFIG_EDF_VERSAL_H */
