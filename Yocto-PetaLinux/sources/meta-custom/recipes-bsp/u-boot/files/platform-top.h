#if defined(CONFIG_ARCH_ZYNQ)
#include <configs/zynq-common.h>
#define CONFIG_SYS_BOOTM_LEN 0x05000000
#undef CONFIG_SYS_BOOTMAPSZ
#endif

#if defined(CONFIG_ARCH_ZYNQMP)
#include <configs/xilinx_zynqmp.h>
#endif