FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Custom Bootscript
SRC_URI += "file://custom.boot.cmd"

do_compile:append() {
    mkimage -A arm -T script -C none -n "Custom U-Boot Script" -d "${WORKDIR}/custom.boot.cmd" boot.scr
}