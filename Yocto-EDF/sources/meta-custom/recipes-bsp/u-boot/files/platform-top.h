#if defined(CONFIG_MICROBLAZE)
#include <configs/microblaze-generic.h>
#define CONFIG_SYS_BOOTM_LEN 0xF000000
#endif
#if defined(CONFIG_ARCH_ZYNQ)
#include <configs/zynq-common.h>
#undef CONFIG_SYS_BOOTMAPSZ
#define CONFIG_SYS_BOOTM_LEN 0x0C800000
#endif
#if defined(CONFIG_ARCH_ZYNQMP)
#include <configs/xilinx_zynqmp.h>
#undef CONFIG_SYS_BOOTMAPSZ
#define CONFIG_SYS_BOOTM_LEN 0x0C800000
#endif
#if defined(CONFIG_ARCH_VERSAL)
#include <configs/xilinx_versal.h>
#endif
#if defined(CONFIG_ARCH_VERSAL_NET)
#include <configs/xilinx_versal_net.h>
#endif