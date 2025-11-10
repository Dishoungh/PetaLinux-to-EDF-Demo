COMPATIBLE_MACHINE:zynqmp = "${MACHINE}"
COMPATIBLE_MACHINE:versal = "${MACHINE}"
COMPATIBLE_MACHINE:versal-2ve-2vm = "${MACHINE}"
COMPATIBLE_MACHINE:zynq = "${MACHINE}"

BOOTBIN_VER_MAX_LEN:versal ?= "50"
BOOTBIN_VER_MAX_LEN:versal-2ve-2vm ?= "60"
BOOTBIN_VER_MAX_LEN:zynq ?= "50"

AMD-EDF_BOOTBIN_VER ?= "1"
AMD-EDF_BOOTBIN_VER_FILE ?= "bootbin-version-header.bin"

python do_configure:prepend:amd-edf() {
    version = d.getVar("AMD-EDF_BOOTBIN_VER")
}

python do_configure:append:amd-edf() {
    edf_version = d.getVar('AMD-EDF_BOOTBIN_VER')
    if d.getVar('SOC_FAMILY') == 'zynqmp':
       edf_version = d.getVar('MACHINE') + '-v' + d.getVar('AMD-EDF_BOOTBIN_VER')
       edf_ver_f = edf_version.encode("utf-8").hex()
    else:
       edf_ver_f = int(edf_version).to_bytes(4, 'little').decode('utf-8')

    with open(d.expand("${B}/${AMD-EDF_BOOTBIN_VER_FILE}"), "w") as f:
       f.write(edf_ver_f)
}

do_deploy:append:amd-edf() {
     install -m 0644 ${B}/${AMD-EDF_BOOTBIN_VER_FILE} ${DEPLOYDIR}/${IMAGE_NAME}.bin
     ln -s ${IMAGE_NAME}.bin ${DEPLOYDIR}/${IMAGE_LINK_NAME}.bin
}
