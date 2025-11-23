FILESEXTRAPATHS:prepend:eval-brd-sc-zynqmp := "${THISDIR}/files:"

SRC_URI:append:eval-brd-sc-zynqmp = " file://zynqmp-sc-revc.dtsi"
SRC_URI:append:eval-brd-sc-zynqmp = " file://0001-Add-eeprom-labels-for-SC-dts.patch"

