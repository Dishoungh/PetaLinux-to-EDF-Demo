FILESEXTRAPATHS:prepend:system-controller-rauc-demo := "${THISDIR}/${PN}:"
SRC_URI:append:system-controller-rauc-demo = " file://edf-sc-boot.cmd"
