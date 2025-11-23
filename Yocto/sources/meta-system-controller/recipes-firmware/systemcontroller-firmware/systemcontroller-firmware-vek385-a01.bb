SUMMARY = "Firmware for the vek385-a01/a02 versal system controller"

PROVIDES += " systemcontroller-firmware-vek385-a02"

include systemcontroller-firmware.inc

FW_DIR = "vek385"
FW_FILENAME = "vek385-a01"

COMPATIBLE_MACHINE:amd-cortexa53-common = "${MACHINE}"

do_install:append() {
    # create symbolic links to supported revisions of the same board
    for board in a01 a02; do
	install -d ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR}-${board}
	ln -sr ${D}${nonarch_base_libdir}/firmware/xilinx/${PN}/${PN}.bin ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR}-${board}/${FW_DIR}-${board}.bin
	ln -sr ${D}${nonarch_base_libdir}/firmware/xilinx/${PN}/${PN}.dtbo ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR}-${board}/${FW_DIR}-${board}.dtbo
	ln -sr ${D}${nonarch_base_libdir}/firmware/xilinx/${PN}/shell.json ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR}-${board}/shell.json
    done
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/xilinx/*"
