SUMMARY = "Recipe to embedded loguru Python Package"
HOMEPAGE ="https://pypi.org/project/loguru"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=17a1d3575545a1e1c7c7f835388beafe"

inherit pypi setuptools3

PYPI_PACKAGE = "loguru"

SRC_URI[md5sum] = "a2c3bde7ed24a843b4e6ed0382bf8311"
SRC_URI[sha256sum] = "066bd06758d0a513e9836fd9c6b5a75bfb3fd36841f4b996bc60b547a309d41c"

RDEPENDS:${PN} += " \
        ${PYTHON_PN}-multiprocessing \
       "

DEPENDS += " \
	python3-wheel-native \
	python3-pip-native \
"

BBCLASSEXTEND = "native"
