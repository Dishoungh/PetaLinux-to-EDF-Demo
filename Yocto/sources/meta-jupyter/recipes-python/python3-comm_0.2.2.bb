SUMMARY = "Jupyter Python Comm implementation, for usage in ipykernel, xeus-python etc."
HOMEPAGE = "https://github.com/ipython/comm"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3a623b76c23287751590a7793a86e552"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "3fd7a84065306e07bea1773df6eb8282de51ba82f77c72f9c85716ab11fe980e"

inherit pypi python_hatchling

RDEPENDS:${PN} = "python3-traitlets"

BBCLASSEXTEND = "native"
