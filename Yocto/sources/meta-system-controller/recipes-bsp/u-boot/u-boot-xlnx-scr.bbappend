FILESEXTRAPATHS:prepend:system-controller := "${THISDIR}/u-boot-xlnx-scr:"

BOOTMODE:system-controller = ""
BOOTFILE_EXT:system-controller = "sc"

SRC_URI:append:system-controller = " file://boot.cmd.sc"

KERNEL_COMMAND_APPEND:append:system-controller = " uio_pdrv_genirq.of_id=generic-uio"
