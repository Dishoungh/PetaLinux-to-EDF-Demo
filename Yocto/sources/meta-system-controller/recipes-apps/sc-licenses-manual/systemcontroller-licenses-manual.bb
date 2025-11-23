SUMMARY = "License Files"
DESCRIPTION = "Recipe to include license files in the root filesystem"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PETALINUX_LIC_URL ??= "https://edf.amd.com/sswreleases/rel-v2023/system-controller/license/"
PETALINUX_LIC_DIR ??= "system-controller-license_041624"


COMPATIBLE_MACHINE:system-controller = "${MACHINE}"
COMPATIBLE_MACHINE:amd-cortexa53-common = "${MACHINE}"

S = "${WORKDIR}"

SRC_URI = "${PETALINUX_LIC_URL}/${PETALINUX_LIC_DIR}.tar.gz"

SRC_URI[sha256sum] = "cc7f70635f93e896d4b85060228e6eb2f7a987728c6c5c551a8ebf7792fd71b9"

do_install() {
	install -d ${D}${datadir}/lic/
	cp ${S}/system-controller-license/* ${D}${datadir}/lic/
}

FILES:${PN} += "${datadir}/lic/*"
