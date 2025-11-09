DESCRIPTION = "Generate the main FPT for Embedded Plus"
SUMMARY = "Generate the main flash partition table for Embedded Plus"

inherit python3native deploy image-artifact-names

require emb-plus-boot-fw-gen.inc

INHIBIT_DEFAULT_DEPS = "1"
IMAGE_NAME_SUFFIX = ""

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:emb-plus-ve2302-xrt = "${MACHINE}"

RCONFLICTS:${PN} = "amr-fpt"

S = "${WORKDIR}/git"

inherit deploy image-artifact-names

do_compile () {
    ${PYTHON} ${S}/gen_fpt_bin.py --fpt ${S}/metadata/rave_ivh/main_fpt.json --output ${WORKDIR}/${PN}.bin
}

do_install() {
    :
}

do_deploy () {
    install -Dm 0644 ${WORKDIR}/${PN}.bin ${DEPLOYDIR}/${IMAGE_NAME}.bin
    ln -sf ${IMAGE_NAME}.bin ${DEPLOYDIR}/${IMAGE_LINK_NAME}.bin
    ln -sf ${IMAGE_NAME}.bin ${DEPLOYDIR}/fpt-${MACHINE}.bin
}

addtask deploy after do_compile
