FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

include ${@bb.utils.contains('DISTRO_FEATURES', 'virtualization', 'edf-virt.inc', '', d)}

# Audit is required or systemd can fail to start the journal
SRC_URI += "file://audit.cfg"

SRC_URI:append:versal-2ve-2vm = " file://edf_misc.cfg"
KERNEL_FEATURES:append:versal-2ve-2vm = " edf_misc.cfg"
