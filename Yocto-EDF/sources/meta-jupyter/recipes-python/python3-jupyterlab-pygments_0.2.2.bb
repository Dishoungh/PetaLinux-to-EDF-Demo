SUMMARY = "Pygments theme using JupyterLab CSS variables"
HOMEPAGE = "https://github.com/jupyterlab/jupyterlab_pygments"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=20a40995a0b2f0ae1f2a70d2dc995bbf"

DEPENDS += "python3-jupyter-packaging-native"

SRC_URI[sha256sum] = "7405d7fde60819d905a9fa8ce89e4cd830e318cdad22a0030f7a901da705585d"

PYPI_PACKAGE = "jupyterlab_pygments"
inherit pypi setuptools3

RDEPENDS:${PN} += "python3-core python3-pygments"

FILES:${PN} += "${prefix}"

BBCLASSEXTEND = "native"
