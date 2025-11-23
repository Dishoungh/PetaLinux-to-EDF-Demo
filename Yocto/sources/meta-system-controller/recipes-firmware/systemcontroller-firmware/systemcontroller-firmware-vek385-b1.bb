SUMMARY = "Firmware for the vek385-b1/b2 versal system controller"

include systemcontroller-firmware.inc

FW_DIR = "vek385-b1"
FW_DIR_B2 = "vek385-b2"
FW_FILENAME = "vek385-b1"

COMPATIBLE_MACHINE:amd-cortexa53-common = "${MACHINE}"

do_install:append() {
    install -d ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR}
    install -d ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR_B2}
    ln -sr ${D}${nonarch_base_libdir}/firmware/xilinx/${PN}/${PN}.bin ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR}/${FW_DIR}.bin
    ln -sr ${D}${nonarch_base_libdir}/firmware/xilinx/${PN}/${PN}.dtbo ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR}/${FW_DIR}.dtbo
    ln -sr ${D}${nonarch_base_libdir}/firmware/xilinx/${PN}/shell.json ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR}/shell.json
    ln -sr ${D}${nonarch_base_libdir}/firmware/xilinx/${PN}/${PN}.bin ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR_B2}/${FW_DIR_B2}.bin
    ln -sr ${D}${nonarch_base_libdir}/firmware/xilinx/${PN}/${PN}.dtbo ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR_B2}/${FW_DIR_B2}.dtbo
    ln -sr ${D}${nonarch_base_libdir}/firmware/xilinx/${PN}/shell.json ${D}${nonarch_base_libdir}/firmware/xilinx/${FW_DIR_B2}/shell.json
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/xilinx/*"
