SUMMARY = "Xilinx EDF packagegroup for GUI on-boarding"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(^$)"
COMPATIBLE_MACHINE:aarch64 = "(.*)"
COMPATIBLE_MACHINE:armv7a = "(.*)"

inherit packagegroup features_check
REQUIRED_DISTRO_FEATURES = "x11 wayland"

PACKAGES = "${PN} ${PN}-weston ${PN}-qt ${PN}-other"

RDEPENDS:${PN} = "\
    ${PN}-weston \
    ${PN}-qt \
    ${PN}-other \
    "

SUMMARY:${PN}-weston = "Xilinx EDF GUI - weston/wayland packages"
RDEPENDS:${PN}-weston = "\
    packagegroup-core-weston \
    wayland \
    wayland-protocols \
    "

SUMMARY:${PN}-qt = "Xilinx EDF GUI - qt packages"
RDEPENDS:${PN}-qt = "\
    packagegroup-xilinx-qt \
    qtsvg \
    qttools \
    qtwebkit \
    qtdeclarative \
    qtimageformats \
    qtquickcontrols \
    "

SUMMARY:${PN}-other = "Xilinx EDF GUI - other misc packages"
RDEPENDS:${PN}-other = "\
    glmark2 \
    ${@bb.utils.contains('DISTRO_FEATURES', 'libmali', '', 'kmscube', d)} \
    mesa-demos \
    vulkan-tools \
    "
