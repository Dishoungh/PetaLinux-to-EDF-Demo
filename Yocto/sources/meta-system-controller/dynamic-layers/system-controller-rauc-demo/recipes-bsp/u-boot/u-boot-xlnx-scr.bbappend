FILESEXTRAPATHS:prepend:system-controller-rauc-demo := "${THISDIR}/u-boot-xlnx-scr:"

BOOTMODE:system-controller-rauc-demo = "rauc"
SRC_URI:append:system-controller-rauc-demo = " file://boot.cmd.raucsc"
PR = 'r1'
