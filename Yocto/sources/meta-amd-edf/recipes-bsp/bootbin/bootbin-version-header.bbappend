COMPATIBLE_MACHINE:zynqmp = "${MACHINE}"
COMPATIBLE_MACHINE:versal = "${MACHINE}"
COMPATIBLE_MACHINE:versal-net = "${MACHINE}"
COMPATIBLE_MACHINE:versal-2ve-2vm = "${MACHINE}"
COMPATIBLE_MACHINE:zynq = "${MACHINE}"

BOOTBIN_VER_MAX_LEN:versal ?= "50"
BOOTBIN_VER_MAX_LEN:versal-net ?= "50"
BOOTBIN_VER_MAX_LEN:versal-2ve-2vm ?= "60"
BOOTBIN_VER_MAX_LEN:zynq ?= "50"

BOOTBIN_ROLLBACK_COUNTER ?= "1"
BOOTBIN_ROLLBACK_COUNTER[doc] ?= "Rollback counter enforces that only firmware newer than the last applied version can be installed"
BOOTBIN_ROLLBACK_COUNTER_FILE ?= "bootbin-version-header.bin"

python do_configure:prepend:amd-edf() {
    version = d.getVar("BOOTBIN_ROLLBACK_COUNTER")
}

python do_configure:append:amd-edf() {
    edf_version = d.getVar('BOOTBIN_ROLLBACK_COUNTER')
    if d.getVar('SOC_FAMILY') == 'zynqmp':
       edf_version = d.getVar('MACHINE') + '-v' + d.getVar('BOOTBIN_ROLLBACK_COUNTER')
       edf_ver_f = edf_version.encode("utf-8").hex()
    else:
       edf_ver_f = int(edf_version).to_bytes(4, 'little').decode('utf-8')

    with open(d.expand("${B}/${BOOTBIN_ROLLBACK_COUNTER_FILE}"), "w") as f:
       f.write(edf_ver_f)
}

do_deploy:append:amd-edf() {
     install -m 0644 ${B}/${BOOTBIN_ROLLBACK_COUNTER_FILE} ${DEPLOYDIR}/${IMAGE_NAME}.bin
     ln -s ${IMAGE_NAME}.bin ${DEPLOYDIR}/${IMAGE_LINK_NAME}.bin
}
