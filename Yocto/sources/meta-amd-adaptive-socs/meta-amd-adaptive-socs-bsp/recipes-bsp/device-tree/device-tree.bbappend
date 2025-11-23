FILESEXTRAPATHS:prepend:versal-vrk160-sdt-seg:linux := "${THISDIR}/files:"
EXTRA_DT_INCLUDE_FILES:append:versal-vrk160-sdt-seg:linux = " versal-vrk160-sdt-seg-system-conf.dtsi"

FILESEXTRAPATHS:prepend:versal-vek280-sdt-seg:linux := "${THISDIR}/files:"
EXTRA_DT_INCLUDE_FILES:append:versal-vek280-sdt-seg:linux = " versal-vek280-sdt-seg-system-conf.dtsi"

FILESEXTRAPATHS:prepend:versal-2ve-2vm-vek385-sdt-seg:linux := "${THISDIR}/files:"
EXTRA_DT_INCLUDE_FILES:append:versal-2ve-2vm-vek385-sdt-seg:linux = " versal-2ve-2vm-vek385-sdt-seg-system-conf.dtsi"

FILESEXTRAPATHS:prepend:versal-2ve-2vm-vek385-revb-sdt-seg:linux := "${THISDIR}/files:"
EXTRA_DT_INCLUDE_FILES:append:versal-2ve-2vm-vek385-revb-sdt-seg:linux = " versal-2ve-2vm-vek385-revb-sdt-seg-system-conf.dtsi"

FILESEXTRAPATHS:prepend:zynq-zc702-sdt-full := "${THISDIR}/files:"
EXTRA_DT_INCLUDE_FILES:append:zynq-zc702-sdt-full = " zynq-zc702-sdt-full-system-conf.dtsi"
