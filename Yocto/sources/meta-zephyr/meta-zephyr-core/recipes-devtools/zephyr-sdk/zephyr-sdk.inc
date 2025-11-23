SUMMARY = "Zephyr SDK Bundle"
DESCRIPTION = "Official SDK built using crosstool-ng, distributed by the \
Zephyr project"
COMPATIBLE_HOST = "(x86_64|aarch64).*-linux"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

INHIBIT_DEFAULT_DEPS = "1"
# CMake is required by the setup script
DEPENDS += "cmake"

SDK_ARCHIVE = "zephyr-sdk-${PV}_linux-${BUILD_ARCH}.tar.xz"
SDK_NAME = "${BUILD_ARCH}"
SRC_URI = "https://github.com/zephyrproject-rtos/sdk-ng/releases/download/v${PV}/${SDK_ARCHIVE};subdir=${S};name=${SDK_NAME}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

ZEPHYR_SDK_DIR = "${prefix}/zephyr-sdk"

do_install() {
    install -d ${D}${prefix}
    cp -r ${S}/zephyr-sdk-${PV} ${D}${ZEPHYR_SDK_DIR}

    # Install host tools
    ${D}${ZEPHYR_SDK_DIR}/setup.sh -h
}

SYSROOT_DIRS += "${ZEPHYR_SDK_DIR}"
INHIBIT_SYSROOT_STRIP = "1"
BBCLASSEXTEND = "native"
