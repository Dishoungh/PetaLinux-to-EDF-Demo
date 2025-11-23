SUMMARY = "System Controller App - VHK158 board specific files"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

require system-controller-app.inc

BOARD = "vhk158"
BOARD_upper = "VHK158"

SRC_URI = "\
	${ES1_PATH} \
	${SYS_PATH} \
	${JPG_PATH} \
	${JSON_PATH} \
	${PNG_PATH} \
	${JS_PATH} \
	${ELF_PATH} \
"

RDEPENDS_${PN} = "packagegroup-syscontroller"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:system-controller = "${MACHINE}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += " \
	${datadir}/system-controller-app/ \
	${datadir}/scweb/static/images/ \
	${datadir}/scweb/static/js/ \
	"

do_install(){
	mkdir -p ${D}${datadir}/system-controller-app/board
	mkdir -p ${D}${datadir}/system-controller-app/BIT
	mkdir -p ${D}${datadir}/system-controller-app/BIT/${BOARD_upper}
	mkdir -p ${D}${datadir}/scweb/static/images/
	mkdir -p ${D}${datadir}/scweb/static/js/

	cp ${WORKDIR}/${BOARD_upper}.json ${D}${datadir}/system-controller-app/board/
	cp ${WORKDIR}/${BOARD}_es1_system_wrapper.pdi ${D}${datadir}/system-controller-app/BIT/${BOARD_upper}/es1_system_wrapper.pdi
	cp ${WORKDIR}/${BOARD}_system_wrapper.pdi ${D}${datadir}/system-controller-app/BIT/${BOARD_upper}/system_wrapper.pdi
	cp ${WORKDIR}/${BOARD}_versal_bit.elf ${D}${datadir}/system-controller-app/BIT/${BOARD_upper}/versal_bit.elf

	cp ${WORKDIR}/${BOARD_upper}_home.png ${D}${datadir}/scweb/static/images/
	cp ${WORKDIR}/${BOARD}.jpg ${D}${datadir}/scweb/static/images/
	cp ${WORKDIR}/${BOARD}_strings.js ${D}${datadir}/scweb/static/js/
}
