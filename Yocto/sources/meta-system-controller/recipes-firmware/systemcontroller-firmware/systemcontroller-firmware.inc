LICENSE = "Proprietary & GPL-2.0-only"
LIC_FILES_CHKSUM = " \
	file://${WORKDIR}/git/LICENSE-BINARIES;md5=fa9b03ada9ffccf51dd60899bf28c702 \
	file://${WORKDIR}/git/LICENSE-GPLv2;md5=9afdcd1be3f71bd3791fa5961075d776 \
	"
BRANCH = "xlnx_rel_v2025.2"
SRC_URI = "git://github.com/Xilinx/versal-sc-firmware.git;protocol=https;branch=${BRANCH}"
SRCREV ?= "14b51ead96b26af7d9d2e893b0aadc46efcfca92"

PR = 'r1'

inherit dfx_user_dts

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:zynqmp-generic = "${MACHINE}"

do_configure[prefuncs] =+ "move_fw_files"
python move_fw_files () {
    import shutil
    import os

    s = d.getVar('S')
    fw_filename = d.getVar('FW_FILENAME')
    pn = d.getVar('PN')
    shutil.move(os.path.join(s, fw_filename + '.bit'), os.path.join(s, pn + '.bit'))
    shutil.move(os.path.join(s, fw_filename + '.dtsi'), os.path.join(s, pn + '.dtsi'))
}
