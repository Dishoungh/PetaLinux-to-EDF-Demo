FILESEXTRAPATHS:prepend:eval-brd-sc-zynqmp := "${THISDIR}/files:"

SRC_URI:append:eval-brd-sc-zynqmp = " file://zynqmp-sc-revc.dtsi"

