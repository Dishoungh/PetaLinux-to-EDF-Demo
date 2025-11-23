SUMMARY = "System Controller App - VEK385 board specific files"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

require system-controller-app.inc

BOARD = "vek385"
BOARD_upper = "VEK385"

FILESEXTRAPATHS:prepend:zynqmp-generic := "${THISDIR}/${BOARD}:"

SRC_URI = "\
	${A01_ES1_PATH} \
	${A02_ES1_PATH} \
	${ES1_PATH} \
	${SYS_PATH} \
	${JPG_PATH} \
	${JSON_PATH} \
	${PNG_PATH} \
	${JS_PATH} \
	${ELF_PATH} \
"

SRC_URI:append:zynqmp-generic = " file://ser2net_vek385_a01.yaml \
                                  file://ser2net_vek385.yaml "

RDEPENDS_${PN} = "packagegroup-syscontroller"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:zynqmp-generic = "${MACHINE}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += " \
	${datadir}/system-controller-app/ \
	${datadir}/scweb/static/images/ \
	${datadir}/scweb/static/js/ \
	${datadir}/config \
	"

do_install(){
	install -d ${D}${datadir}/system-controller-app/board
	install -d ${D}${datadir}/system-controller-app/BIT
	install -d ${D}${datadir}/system-controller-app/BIT/${BOARD_upper}
	install -d ${D}${datadir}/scweb/static/images/
	install -d ${D}${datadir}/scweb/static/js/
	install -d ${D}${datadir}/config

	cp ${WORKDIR}/${BOARD_upper}.json ${D}${datadir}/system-controller-app/board/
	cp ${WORKDIR}/${BOARD}_A01_es1_system_wrapper.pdi ${D}${datadir}/system-controller-app/BIT/${BOARD_upper}/A01_es1_system_wrapper.pdi
	cp ${WORKDIR}/${BOARD}_A02_es1_system_wrapper.pdi ${D}${datadir}/system-controller-app/BIT/${BOARD_upper}/A02_es1_system_wrapper.pdi
	cp ${WORKDIR}/${BOARD}_es1_system_wrapper.pdi ${D}${datadir}/system-controller-app/BIT/${BOARD_upper}/es1_system_wrapper.pdi
	#cp ${WORKDIR}/${BOARD}_system_wrapper.pdi ${D}${datadir}/system-controller-app/BIT/${BOARD_upper}/system_wrapper.pdi
	cp ${WORKDIR}/${BOARD}_versal_bit.elf ${D}${datadir}/system-controller-app/BIT/${BOARD_upper}/versal_bit.elf

	cp ${WORKDIR}/${BOARD_upper}_home.png ${D}${datadir}/scweb/static/images/
	cp ${WORKDIR}/${BOARD}.jpg ${D}${datadir}/scweb/static/images/
	cp ${WORKDIR}/${BOARD}_strings.js ${D}${datadir}/scweb/static/js/
	
	cp ${WORKDIR}/ser2net_vek385_a01.yaml ${D}${datadir}/config
	cp ${WORKDIR}/ser2net_vek385.yaml ${D}${datadir}/config
	ln -sr ${D}${datadir}/config/ser2net_vek385_a01.yaml ${D}${datadir}/config/ser2net_vek385_a02.yaml
}
