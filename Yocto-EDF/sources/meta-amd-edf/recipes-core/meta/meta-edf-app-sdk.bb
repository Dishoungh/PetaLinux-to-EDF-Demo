SUMMARY = "Package for building a installable toolchain for AMD Embedded Development Framework SDK"
LICENSE = "MIT"

PR = "r0"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:amd-cortexa53-common = "${MACHINE}"
COMPATIBLE_MACHINE:amd-cortexa53-mali-common = "${MACHINE}"
COMPATIBLE_MACHINE:amd-cortexa72-common = "${MACHINE}"
COMPATIBLE_MACHINE:amd-cortexa78-common = "${MACHINE}"
COMPATIBLE_MACHINE:amd-cortexa78-mali-common = "${MACHINE}"
COMPATIBLE_MACHINE:kria-zynqmp-generic = "${MACHINE}"

inherit populate_sdk amd-qemu-xilinx-sdk-tools amd-misc-sdk-tools

# add these items to the "cross" side of the SDK
TOOLCHAIN_TARGET_TASK:append = " \
    packagegroup-vitis-aiml-dev \
    kernel-devsrc \
    xrt-dev \
    "

# add these items to the "native" side of the SDK
# i.e. these tools are built to run on the build host
TOOLCHAIN_HOST_TASK:append = " \
	nativesdk-packagegroup-vitis-aiml \
	nativesdk-packagegroup-edf-tools \
	"
