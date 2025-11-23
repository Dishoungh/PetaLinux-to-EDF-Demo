do_compile:append:eval-brd-sc-zynqmp() {
    printf "* ${PN}\nSRCREV: ${SRCREV}\nBRANCH: ${BRANCH}\n\n" > ${S}/${PN}.manifest
}

do_deploy:append:eval-brd-sc-zynqmp() {
    install -m 0644 ${S}/${PN}.manifest ${DEPLOYDIR}/${FSBL_BASE_NAME}.manifest
    ln -sf ${FSBL_BASE_NAME}.manifest ${DEPLOYDIR}/${FSBL_IMAGE_NAME}.manifest
}

YAML_SERIAL_CONSOLE_STDIN:zynqmp-k24-sc-xsct-base ?= "psu_uart_1"
YAML_SERIAL_CONSOLE_STDOUT:zynqmp-k24-sc-xsct-base ?= "psu_uart_1"
YAML_COMPILER_FLAGS:append:zynqmp-k24-sc-xsct-base ?= " -DFSBL_DEBUG"

YAML_ENABLE_APU_AS_OVERLAY_CONFIG_MASTER:zynqmp-k24-sc-xsct-base = "1"
