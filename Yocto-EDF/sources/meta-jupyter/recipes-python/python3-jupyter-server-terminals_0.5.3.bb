SUMMARY = "A Jupyter Server Extension Providing Terminals."
HOMEPAGE = "https://jupyter.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ee82bc15ab23966cc24cc4e361736bda"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "5ae0295167220e9ace0edcfdb212afd2b01ee8d179fe6f23c899590e9b8a5269"

PYPI_PACKAGE = "jupyter_server_terminals"
inherit pypi python_hatchling

FILES:${PN} += "${prefix}"

RDEPENDS:${PN} = "python3-terminado"

BBCLASSEXTEND = "native"
