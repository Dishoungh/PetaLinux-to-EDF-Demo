FILESEXTRAPATHS:prepend := "${THISDIR}/u-boot-xlnx:"
FILESEXTRAPATHS:prepend:zynqmp := "${THISDIR}/zynqmp:"
FILESEXTRAPATHS:prepend:versal := "${THISDIR}/versal:"
FILESEXTRAPATHS:prepend:versal-2ve-2vm := "${THISDIR}/versal-2ve-2vm:"

SRC_URI:append:zynqmp = " file://edf-env.cfg file://amd_edf.h"
SRC_URI:append:versal = " file://edf-env.cfg file://amd_edf.h file://amd_edf_common.h"
SRC_URI:append:versal-2ve-2vm = " file://edf-env.cfg file://amd_edf.h file://amd_edf_common.h"

SRC_URI:append:versal:amd-edf = " file://bootcmd-bootefi.cfg"

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
    if [ -e ${WORKDIR}/amd_edf_common.h ]; then
        cp ${WORKDIR}/amd_edf_common.h ${S}/include/configs/amd_edf_common.h
    fi
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

        # There is a 33 byte checksum added to the environment, subtract this from the size
        UBOOT_ENV_SIZE=$(printf "%d" $UBOOT_ENV_SIZE)
        UBOOT_ENV_SIZE=$(expr $UBOOT_ENV_SIZE - 33)

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
