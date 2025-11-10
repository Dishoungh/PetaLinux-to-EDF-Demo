DESCRIPTION = "Generate the Embedded-Plus Flash Partition Table(FPT) - AMR"
SUMMARY = "Adaptive Management Runtime(AMR) compoment"

inherit python3native deploy image-artifact-names

require recipes-bsp/amr/amr.inc

INHIBIT_DEFAULT_DEPS = "1"
IMAGE_NAME_SUFFIX = ""

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:emb-plus-ve2302-amr = "${MACHINE}"

RCONFLICTS:${PN} = "vmr-fpt"

S = "${WORKDIR}/git/fw/AMC"

do_compile() {
    ${PYTHON} ${S}/scripts/gen_fpt.py -f ${S}/fpt/fpt.json -o ${WORKDIR}
}

do_install() {
    :
}

do_deploy() {
    install -Dm 0644 ${WORKDIR}/amr_fpt.bin ${DEPLOYDIR}/${IMAGE_NAME}.bin
    ln -sf ${IMAGE_NAME}.bin ${DEPLOYDIR}/${IMAGE_LINK_NAME}.bin
    ln -sf ${IMAGE_NAME}.bin ${DEPLOYDIR}/fpt-${MACHINE}.bin
}

addtask deploy after do_compile
