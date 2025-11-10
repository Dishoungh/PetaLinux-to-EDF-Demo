include ${@bb.utils.contains('DISTRO_FEATURES', 'virtualization', 'edf-virt.inc', '', d)}

FILESEXTRAPATHS:prepend:versal-2ve-2vm := "${THISDIR}/files:"

SRC_URI:append:versal-2ve-2vm = " file://edf_misc.cfg"
KERNEL_FEATURES:append:versal-2ve-2vm = " edf_misc.cfg"
