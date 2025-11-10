SUMMARY = "Jupyter core package. A base package on which Jupyter projects rely."
HOMEPAGE = "https://jupyter.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a5cc41e8bc83e8e689ce2c7bb0ceba14"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "aa5f8d32bbf6b431ac830496da7392035d6f61b4f54872f15c4bd2a9c3f536d9"

PYPI_PACKAGE = "jupyter_core"
inherit pypi python_hatchling

RDEPENDS:${PN} = " \
	python3-platformdirs \
	python3-traitlets \
	"

BBCLASSEXTEND = "native"
