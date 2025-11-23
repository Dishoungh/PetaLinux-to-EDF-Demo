inherit amd-zephyr-sdt

# To use SRC_URI_ZEPHYR pointing to zephyr-amd github tree instead of upstream
# zephyr tree we need add amd-zephyr-kernel-src_${PREFERRED_VERSION_zephyr-kernel}_${XILINX_RELEASE_VERSION}
# include file.
require amd-zephyr-kernel-src_${PREFERRED_VERSION_zephyr-kernel}_${XILINX_RELEASE_VERSION}.inc

SRC_URI_ZEPHYR_OPEN_AMP_SYS_REF = "git://github.com/Xilinx/openamp-system-reference.git;protocol=https"
BRANCH  = "main"
SRCREV_open-amp-sys-ref = "e1590054897e8a21bf8d1d808944b18690cb9dce"

ZEPHYR_SDT_SOC_KCONFIG="${ZEPHYR_SOC_KCONFIG}"
ZEPHYR_SDT_SOC_KCONFIG_DEFCONFIG="${ZEPHYR_SOC_KCONFIG_DEFCONFIG}"
ZEPHYR_SOC_VENDOR="amd"
BOARD="${ZEPHYR_BOARD}"
ZEPHYR_SOC_FAMILY="${ZEPHYR_BOARD}"

EXTRA_OECMAKE += " \
    -DOVERLAY_CONFIG="${S}/open-amp-sys-ref/examples/zephyr/rpmsg_multi_services/boards/${BOARD}.conf" \
    "
