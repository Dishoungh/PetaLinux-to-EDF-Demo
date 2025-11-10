#ifndef __CONFIG_EDF_VERSAL_H
#define __CONFIG_EDF_VERSAL_H

#include <configs/xilinx_versal.h>

#define ENV_MEM_LAYOUT_SETTINGS \
    "fdt_addr_r=0x1000\0" \
    "fdt_size_r=0x400000\0" \
    "pxefile_addr_r=0x10000000\0" \
    "kernel_addr_r=0x200000\0" \
    "kernel_size_r=0x10000000\0" \
    "kernel_comp_addr_r=0x30000000\0" \
    "kernel_comp_size=0x3C00000\0" \
    "ramdisk_addr_r=0x02100000\0" \
    "script_size_f=0x80000\0"

/* Initial environment variables */
#ifndef CFG_EXTRA_ENV_SETTINGS
#define CFG_EXTRA_ENV_SETTINGS \
    ENV_MEM_LAYOUT_SETTINGS \
    BOOTENV
#endif

#endif /* __CONFIG_EDF_VERSAL_H */
