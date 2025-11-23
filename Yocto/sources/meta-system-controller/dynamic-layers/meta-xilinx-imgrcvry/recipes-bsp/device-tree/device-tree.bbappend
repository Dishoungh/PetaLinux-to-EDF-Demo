FILESEXTRAPATHS:prepend:zynqmp-k24-sc-sdt-base:imgrcvry := "${THISDIR}/files:"
FILESEXTRAPATHS:prepend:zynqmp-k24-sc-sdt-base:linux := "${THISDIR}/files:"



EXTRA_DT_INCLUDE_FILES:append:zynqmp-k24-sc-sdt-base:imgrcvry = " imgrcvry-zynqmp-system-conf.dtsi"
EXTRA_DT_INCLUDE_FILES:append:zynqmp-k24-sc-sdt-base:linux = " imgrcvry-zynqmp-system-conf.dtsi"
