do_configure:append:eval-brd-sc-zynqmp() {
    printf "* ${PN}\nSRCREV: ${SRCREV}\nBRANCH: ${BRANCH}\n\n" > ${S}/device-tree-${MACHINE}.manifest
}

do_deploy:append:eval-brd-sc-zynqmp() {
    install -m 0644 ${S}/device-tree-${MACHINE}.manifest ${DEPLOYDIR}/
}
include device-tree-sc-${XILINX_XSCT_VERSION}.inc
