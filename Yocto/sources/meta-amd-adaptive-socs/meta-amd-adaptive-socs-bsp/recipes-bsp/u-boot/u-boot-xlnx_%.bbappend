FILESEXTRAPATHS:prepend:versal-2ve-2vm-vek385-sdt-seg := "${THISDIR}/u-boot-xlnx:"
FILESEXTRAPATHS:prepend:versal-2ve-2vm-vek385-revb-sdt-seg := "${THISDIR}/u-boot-xlnx:"
FILESEXTRAPATHS:prepend:zynq-zc702-sdt-full := "${THISDIR}/u-boot-xlnx:"

SRC_URI:append:versal-2ve-2vm-vek385-sdt-seg = "\
    file://u-boot-misc.cfg \
    "
SRC_URI:append:versal-2ve-2vm-vek385-revb-sdt-seg = "\
    file://u-boot-misc.cfg \
    "
SRC_URI:append:zynq-zc702-sdt-full = "\
    file://u-boot-misc_zc702.cfg \
    "
