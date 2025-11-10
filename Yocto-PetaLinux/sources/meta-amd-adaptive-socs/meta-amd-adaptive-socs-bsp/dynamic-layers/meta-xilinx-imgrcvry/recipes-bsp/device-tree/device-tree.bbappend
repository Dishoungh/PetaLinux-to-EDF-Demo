FILESEXTRAPATHS:prepend:versal-vek280-sdt-seg:imgrcvry := "${THISDIR}/files:"
FILESEXTRAPATHS:prepend:versal-vek280-sdt-seg:linux := "${THISDIR}/files:"

EXTRA_DT_INCLUDE_FILES:append:versal-vek280-sdt-seg:imgrcvry = " imgrcvry-system-conf.dtsi"
EXTRA_DT_INCLUDE_FILES:append:versal-vek280-sdt-seg:linux    = " imgrcvry-system-conf.dtsi"
