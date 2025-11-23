SRC_URI = "git://github.com/Xilinx/lopper.git;branch=xlnx_rel_v2025.2;protocol=https"
SRCREV = "ca7782e3bb30522d2388db15d62710ff1d65394a"

FILESEXTRAPATHS:prepend := "${THISDIR}/lopper:"

BASEVERSION = "1.3.0"

RDEPENDS:${PN} += " \
    python3-ruamel-yaml \
    python3-anytree \
    python3-six \
    python3-pyyaml \
    python3-packaging \
    "
