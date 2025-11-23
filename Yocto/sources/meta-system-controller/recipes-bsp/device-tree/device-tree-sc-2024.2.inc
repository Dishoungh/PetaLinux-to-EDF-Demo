FILESEXTRAPATHS:prepend:eval-brd-sc-zynqmp := "${THISDIR}/files:"
FILESEXTRAPATHS:prepend:vck-sc-zynqmp := "${THISDIR}/files:"

SRC_URI:append:eval-brd-sc-zynqmp = " file://0001-Add-eeprom-labels-for-SC-dts.patch"
SRC_URI:append:vck-sc-zynqmp = "  file://0001-Add-eeprom-labels-for-SC-dts.patch file://0002-Add-fclk-to-vck190.patch"
