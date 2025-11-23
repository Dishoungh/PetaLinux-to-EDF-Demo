DESCRIPTION = "TSN user space miscellaneous utilities"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=44e6f1bcc9f4ca675262918839e8663a"

BRANCH = "main"
SRC_URI = "git://github.com/Xilinx/tsn-utils.git;branch=${BRANCH};protocol=https"
SRCREV = "f530f2f37646e0bebfc6a2a528de997a8b6f3885"

S = "${WORKDIR}/git"

inherit autotools-brokensep

DEPENDS = "libconfig"
RDEPENDS:${PN} += "python3-libconf bash"
