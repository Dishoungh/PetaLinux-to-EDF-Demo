COMPATIBLE_MACHINE:versal = "${MACHINE}"
COMPATIBLE_MACHINE:versal-2ve-2vm = "${MACHINE}"

BOOTFW_VERSION_STRING ?= "amd-edf-${MACHINE}-bootfw-v${DISTRO_VERSION}"

python do_configure() {
    version_string = d.getVar('BOOTFW_VERSION_STRING')
    with open(d.expand("${B}/${BOOTBIN_VER_FILE}"), "w") as f:
        f.write(version_string)
}
