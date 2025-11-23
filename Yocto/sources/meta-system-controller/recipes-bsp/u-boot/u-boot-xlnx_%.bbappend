SRC_URI:append:eval-brd-sc-zynqmp = " file://sc_u-boot.cfg"
SRC_URI:append:eval-brd-sc-zynqmp = " file://sc_fru.cfg"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

UBOOT_MANIFEST = "${UBOOT_BINARYNAME}-${MACHINE}-${PV}-${PR}.manifest"

DEPENDS:append:eval-brd-sc-zynqmp = " uboot-device-tree"
DEPENDS:remove:eval-brd-sc-zynqmp-sdt = " uboot-device-tree"

do_compile:append:eval-brd-sc-zynqmp() {
    printf "* ${PN}\nSRCREV: ${SRCREV}\nBRANCH: ${UBRANCH}\n\n" > ${S}/${PN}.manifest
}

do_deploy:append:eval-brd-sc-zynqmp() {
    install -m 0644 ${S}/${PN}.manifest ${DEPLOYDIR}/${UBOOT_MANIFEST}
    ln -sf ${UBOOT_MANIFEST} ${DEPLOYDIR}/${UBOOT_BINARYNAME}-${MACHINE}.manifest
}

PACKAGE_UBOOT_DTB_NAME:eval-brd-sc-zynqmp = "uboot-device-tree.dtb"
# u-boot blob generation configuration for system controller
UBOOT_IMAGE_BLOB_DEFAULT:eval-brd-sc-zynqmp = "1"
DT_BLOB_DIR:eval-brd-sc-zynqmp = "${B}/arch/arm/dts/dt-blob"

IMPORT_CC_DTBS:eval-brd-sc-zynqmp = " \
			zynqmp-sc-vpk120-revB.dtbo:zynqmp-sc-revB.dtb:zynqmp-vpk120-revB01.dtb \
			zynqmp-sc-vpk180-revA.dtbo:zynqmp-sc-revB.dtb:zynqmp-vpk180-revA01.dtb \
			zynqmp-sc-vpk180-revB.dtbo:zynqmp-sc-revB.dtb:zynqmp-vpk180-revB01.dtb \
			zynqmp-sc-vhk158-revA.dtbo:zynqmp-sc-revB.dtb:zynqmp-vhk158-revA01.dtb \
			zynqmp-sc-vek280-revA.dtbo:zynqmp-sc-revB.dtb:zynqmp-vek280-revA01.dtb \
			zynqmp-sc-vek280-revB.dtbo:zynqmp-sc-revC.dtb:zynqmp-vek280-revB01.dtb \
			"

CC_DTBS_DUP:eval-brd-sc-zynqmp = " \
			zynqmp-vhk158-revA01:zynqmp-vhk158-revB01 \
			zynqmp-vek280-revB01:zynqmp-vek280-revB02 \
			zynqmp-vek280-revB01:zynqmp-vek280-revB03 \
			"

SRC_URI:remove:zynqmp-k24-sc-xsct-base = "file://sc_fru.cfg"
DEPENDS:remove:zynqmp-k24-sc-xsct-base = " uboot-device-tree"
