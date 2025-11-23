SUMMARY = "Generate capsule update metadata"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "u-boot-tools-native virtual/boot-bin gcab-native"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy image-artifact-names

IMAGE_NAME_SUFFIX = ""

INHIBIT_DEFAULT_DEPS = "1"

#UEFI variables
UEFI_CAB_HOMEPAGE ?= ""
UEFI_CAB_RELEASE_DESCRIPTION ?= ""
UEFI_CAB_GUID ?= "cb27e54d-8f3a-4c77-8a72-1c76d2d4e938"
UEFI_METADATA_GUID ?= "${@reverse_guid_for_metadata(d.getVar('UEFI_CAB_GUID'))}"
LOC_GUID ?= "588aced7-2cce-ed11-81cd-d324e93ac223"
IMG_0_GUID_0 ?= "0b931b7e-b2f6-11ef-8565-eb65d140066b"
IMG_0_GUID_1 ?= "1243d800-b2f6-11ef-8f4f-8bddc3aa326d"
UEFI_CAB_DEVELOPER_NAME ?= "AMD"
UEFI_CAB_FW_NAME ?= "BootFW"
UEFI_CAB_FW_SUMMARY ?= "Boot Firmware Update"
UEFI_CAB_FW_DESCRIPTION ?= "EDF UEFI BootFW Capsule Update"
UEFI_CAB_FW_ID ?= "${@d.getVar('UEFI_CAB_GUID')[0:8]}"
UEFI_CAB_ID ?= "com.vendor.mydevice.firmware"
UEFI_CAB_VERSION ?= "1.0.0"
UEFI_CAB_UPDATE_PROTOCOL ?= "org.uefi.capsule"
UEFI_CAB_VERSION_FORMAT ?= "number"


def reverse_guid_for_metadata(guid):
    '''First 16 chars of GUID should be in reverse for metadata
    so that uboot will read and display them properly, this function
    will get the guid and reverse them''' 
    metadata_guid = ''
    for id in guid.split('-')[:3]:
       _id = [id[i:i+2] for i in range(0, len(id), 2)]
       metadata_guid += ''.join(_id[::-1]) + '-'
    metadata_guid += '-'.join(guid.split('-')[3:])
    return metadata_guid

    
gen_uefi_metainfo_xml() {
    #Create metainfo xml file to create efi capsule
    cat > ${WORKDIR}/${PN}.metainfo.xml <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<component type="firmware">
  <id>org.${UEFI_CAB_DEVELOPER_NAME}.guid${UEFI_CAB_FW_ID}</id>
  <name>${UEFI_CAB_FW_NAME}</name>
  <summary>${UEFI_CAB_FW_SUMMARY}</summary>
  <description>
    <p>${UEFI_CAB_FW_DESCRIPTION}</p>
  </description>
  <provides>
    <firmware type="flashed">${UEFI_CAB_GUID}</firmware>
  </provides>
  <url type="homepage">${UEFI_CAB_HOMEPAGE}</url>
  <metadata_license>CC0-1.0</metadata_license>
  <project_license>proprietary</project_license>
  <developer_name>${UEFI_CAB_DEVELOPER_NAME}</developer_name>
  <releases>
    <release version="${UEFI_CAB_VERSION}" date="${DATE}">
      <description>
        <p>${UEFI_CAB_RELEASE_DESCRIPTION}</p>
      </description>
    </release>
  </releases>
  <custom>
    <value key="LVFS::VersionFormat">${UEFI_CAB_VERSION_FORMAT}</value>
    <value key="LVFS::UpdateProtocol">${UEFI_CAB_UPDATE_PROTOCOL}</value>
  </custom>
</component>
EOF
}


do_configure() {
    echo -e -n $'\x04' > ${WORKDIR}/${PN}-vendor.txt
    dd if=/dev/zero of=${WORKDIR}/${PN}-vendor.txt seek=1 bs=1 count=3
    gen_uefi_metainfo_xml
}

do_compile() {
    # Generate efi metadata
    mkfwumdata -a 0 -b 2 -i 1 -v 2 ${LOC_GUID},${UEFI_METADATA_GUID},${IMG_0_GUID_0},${IMG_0_GUID_1} \
        ${WORKDIR}/${PN}-metadata.bin -V ${WORKDIR}/${PN}-vendor.txt
    # Generate capsule file, file name should be firmware.bin
    mkeficapsule -o 0x8000 -g ${UEFI_CAB_GUID} ${RECIPE_SYSROOT}/boot/BOOT.bin \
        --index 1 ${WORKDIR}/firmware.bin
    # Generate acceptance capsule bin
    mkeficapsule -A -g ${UEFI_CAB_GUID} ${WORKDIR}/${PN}-bootfw-acceptance-capsule.bin
    # Generate cabinate file for fwupdtool
    gcab -c -n ${WORKDIR}/${PN}-bootfw-firmware.cab ${WORKDIR}/firmware.bin \
        ${WORKDIR}/${PN}.metainfo.xml

}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -Dm 0644 ${WORKDIR}/${PN}-metadata.bin ${DEPLOYDIR}/${IMAGE_NAME}-metadata.bin
    ln -sf ${IMAGE_NAME}-metadata.bin ${DEPLOYDIR}/${IMAGE_LINK_NAME}-metadata.bin

    install -Dm 0644 ${WORKDIR}/firmware.bin ${DEPLOYDIR}/${IMAGE_NAME}-capsule.bin
    ln -sf ${IMAGE_NAME}-capsule.bin ${DEPLOYDIR}/${IMAGE_LINK_NAME}-capsule.bin

    install -Dm 0644 ${WORKDIR}/${PN}-bootfw-acceptance-capsule.bin ${DEPLOYDIR}/${IMAGE_NAME}-acceptance-capsule.bin
    ln -sf ${IMAGE_NAME}-acceptance-capsule.bin ${DEPLOYDIR}/${IMAGE_LINK_NAME}-acceptance-capsule.bin

    install -Dm 0644 ${WORKDIR}/${PN}-bootfw-firmware.cab ${DEPLOYDIR}/${IMAGE_NAME}-bootfw-firmware.cab
    ln -sf ${IMAGE_NAME}-bootfw-firmware.cab ${DEPLOYDIR}/${IMAGE_LINK_NAME}-bootfw-firmware.cab
}

addtask do_deploy after do_compile
