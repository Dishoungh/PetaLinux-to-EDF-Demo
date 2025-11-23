BOOTBIN_DEPENDS ?= ""
BOOTBIN_DEPENDS:append:amd-edf = " bootbin-version-header:do_deploy"
BOOTBIN_DEPENDS:append:versal = " bootbin-version-string:do_deploy"
BOOTBIN_DEPENDS:append:versal-2ve-2vm = " bootbin-version-string:do_deploy base-pdi-unique-id:do_deploy"
do_configure[depends] += "${BOOTBIN_DEPENDS}"

BIF_PARTITION_ATTR:append:amd-edf = "${@' bootbin-version-header' if d.getVar('SOC_FAMILY') == 'zynqmp' else ''}"
BIF_PARTITION_ATTR[bootbin-version-header] = "udf_bh"
BIF_PARTITION_IMAGE[bootbin-version-header] = "${DEPLOY_DIR_IMAGE}/bootbin-version-header-${MACHINE}.bin"

BIF_OPTIONAL_DATA:append:amd-edf = "${@'${DEPLOY_DIR_IMAGE}/bootbin-version-header-${MACHINE}.bin, id=0x22;' if d.getVar('SOC_FAMILY') != 'zynqmp' else ''}"
BIF_OPTIONAL_DATA:append:versal = "${DEPLOY_DIR_IMAGE}/bootbin-version-string-${MACHINE}.txt, id=0x21;"
BIF_OPTIONAL_DATA:append:versal-2ve-2vm = "${DEPLOY_DIR_IMAGE}/bootbin-version-string-${MACHINE}.txt, id=0x21;"
BIF_OPTIONAL_DATA:append:versal-2ve-2vm = "${DEPLOY_DIR_IMAGE}/base-pdi-unique-id-${MACHINE}.txt, id=0x23;"

# For EDF we want zynq to boot using u-boot, not directly to the kernel
QB_DEFAULT_KERNEL:zynq = "u-boot.elf"
