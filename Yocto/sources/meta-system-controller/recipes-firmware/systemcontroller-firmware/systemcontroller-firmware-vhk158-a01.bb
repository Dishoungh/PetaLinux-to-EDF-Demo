SUMMARY = "Firmware for the vhk158-a01/b01 versal system controller"

PROVIDES += "systemcontroller-firmware-vhk158-b01"

include systemcontroller-firmware.inc

FW_DIR = "vhk158"
FW_FILENAME = "vhk158-a01"

do_install:append() {
    # create symbolic links to supported revisions of the same board
    for board in a01 b01; do
        install -d ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR}-${board}
        ln -sr ${D}${nonarch_base_libdir}/firmware/xilinx/${PN}/${PN}.bin ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR}-${board}/${FW_DIR}-${board}.bin
        ln -sr ${D}${nonarch_base_libdir}/firmware/xilinx/${PN}/${PN}.dtbo ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR}-${board}/${FW_DIR}-${board}.dtbo
        ln -sr ${D}${nonarch_base_libdir}/firmware/xilinx/${PN}/shell.json ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR}-${board}/shell.json
    done
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/xilinx/*"
