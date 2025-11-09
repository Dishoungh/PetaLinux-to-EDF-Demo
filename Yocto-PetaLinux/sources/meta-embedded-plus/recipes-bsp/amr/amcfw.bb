DESCRIPTION = "Generate Embedded-Plus Adaptive Management Controller (AMC) application image"
SUMMARY = "Adaptive Management Runtime(AMR) compoment"

LICENSE = "CLOSED"

PROVIDES = "virtual/amc"

INHIBIT_DEFAULT_DEPS = "1"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:emb-plus-ve2302-amr = "${MACHINE}"

# Since we're just copying, we can run any config
COMPATIBLE_HOST = ".*"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Default expects the user to provide the plm-firmware in the deploy
# directory, named "plm-${MACHINE}.elf"
# A machine, multiconfig, or local.conf should override this
AMC_MCDEPENDS ??= "mc::emb-plus-ve2302-amr-cortexr5-0-freertos:amc-firmware:do_deploy"
AMC_DEPLOY_DIR ??= "${TMPDIR}-emb-plus-ve2302-amr-cortexr5-0-freertos/deploy/images/${MACHINE}"
AMC_DEPLOY_DIR[vardepsexclude] += "TOPDIR"
AMC_IMAGE_NAME ??= "amc-firmware-${MACHINE}"

# Default is for the multilib case (without the extension .elf)
AMC_FILE ??= "${AMC_DEPLOY_DIR}/${AMC_IMAGE_NAME}"
AMC_FILE[vardepsexclude] = "AMC_DEPLOY_DIR"

do_fetch[mcdepends] += "${AMC_MCDEPENDS}"

inherit deploy

do_install() {
    if [ ! -e ${AMC_FILE}.elf ]; then
        echo "Unable to find AMC_FILE (${AMC_FILE}.elf)"
        exit 1
    fi

    install -Dm 0644 ${AMC_FILE}.elf ${D}/boot/${PN}.elf
}

# If the item is already in OUR deploy_image_dir, nothing to deploy!
SHOULD_DEPLOY = "${@'false' if (d.getVar('AMC_FILE')).startswith(d.getVar('DEPLOY_DIR_IMAGE')) else 'true'}"
do_deploy() {
    # If the item is already in OUR deploy_image_dir, nothing to deploy!
    if ${SHOULD_DEPLOY}; then
        install -Dm 0644 ${AMC_FILE}.elf ${DEPLOYDIR}/${AMC_IMAGE_NAME}.elf
    fi
}

addtask deploy before do_build after do_install

INSANE_SKIP:${PN} = "arch"
INSANE_SKIP:${PN}-dbg = "arch"

# Disable buildpaths QA check warnings.
INSANE_SKIP:${PN} += "buildpaths"

SYSROOT_DIRS += "/boot"
FILES:${PN} = "/boot/${PN}.elf"
