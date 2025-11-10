FILESEXTRAPATHS:prepend:zynqmp := "${THISDIR}/zynqmp:"
FILESEXTRAPATHS:prepend:zynqmp-k24-sc-sdt-base := "${THISDIR}/zynqmp:"
FILESEXTRAPATHS:prepend:versal := "${THISDIR}/versal:"
FILESEXTRAPATHS:prepend:versal-2ve-2vm := "${THISDIR}/versal-2ve-2vm:"

SRC_URI:append:zynqmp = " file://edf-env.cfg file://amd_edf.h"
SRC_URI:append:versal = " file://edf-env.cfg file://amd_edf.h"
SRC_URI:append:versal-2ve-2vm = " file://edf-env.cfg file://amd_edf.h"
SRC_URI:append:zynqmp-k24-sc-sdt-base = " file://0001-Configure-dwc3-as-host-when-dr_mode-is-otg.patch"

# Generate U-Boot environment binary image

DEPENDS += "u-boot-tools-xlnx-native"

do_unpack:append:zynqmp() {
    bb.build.exec_func('do_sys_config', d)
}

do_unpack:append:versal() {
    bb.build.exec_func('do_sys_config', d)
}

do_unpack:append:versal-2ve-2vm() {
    bb.build.exec_func('do_sys_config', d)
}

do_sys_config() {
    cp ${WORKDIR}/amd_edf.h ${S}/include/configs/amd_edf.h
}

do_compile:append() {
    if [ -n "${UBOOT_INITIAL_ENV}" ]; then
        UBOOT_ENV_SIZE="$(cat ${B}/.config | grep "^CONFIG_ENV_SIZE=" | cut -d'=' -f2)"

        if [ -z "$UBOOT_ENV_SIZE" ]; then
            bberror "Unable to read CONFIG_ENV_SIZE"
        fi

        REDUND=""
        if grep -q "^CONFIG_SYS_REDUNDAND_ENVIRONMENT=y" ${B}/.config; then
            REDUND="-r"
        fi

        echo "Constructing u-boot-initial-env with size $UBOOT_ENV_SIZE"
        uboot-mkenvimage $REDUND -s $UBOOT_ENV_SIZE ${B}/${config}/u-boot-initial-env -o ${B}/u-boot-initial-env.bin
    fi
}

do_deploy:append() {
    if [ -n "${UBOOT_INITIAL_ENV}" ]; then
        install -D -m 644 ${B}/u-boot-initial-env.bin ${DEPLOYDIR}/${UBOOT_INITIAL_ENV}-${MACHINE}-${PV}-${PR}.bin
        ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${PV}-${PR}.bin ${DEPLOYDIR}/${UBOOT_INITIAL_ENV}-${MACHINE}.bin
        ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${PV}-${PR}.bin ${DEPLOYDIR}/${UBOOT_INITIAL_ENV}.bin
    fi
}
