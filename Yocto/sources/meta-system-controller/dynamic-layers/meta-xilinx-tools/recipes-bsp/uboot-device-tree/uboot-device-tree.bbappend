FILESEXTRAPATHS:prepend:eval-brd-sc-zynqmp := "${THISDIR}/files:"

UBOOT_DTS_NAME = "system-sc-revc-zynqmp"

do_configure:append:eval-brd-sc-zynqmp() {
	echo '#include "zynqmp-sc-revc.dtsi"' >> ${DT_FILES_PATH}/system-top.dts
}

include uboot-device-tree-${XILINX_XSCT_VERSION}.inc
