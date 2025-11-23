FILESEXTRAPATHS:prepend:zynqmp-k24-sc-sdt-base := "${THISDIR}/files:"

SRC_URI:append:zynqmp-k24-sc-sdt-base = " file://sc_u-boot-edf.cfg"
