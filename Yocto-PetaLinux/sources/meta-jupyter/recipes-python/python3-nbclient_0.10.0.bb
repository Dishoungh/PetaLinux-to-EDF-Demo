SUMMARY = "A client library for executing notebooks. Formerly nbconvert's ExecutePreprocessor."
HOMEPAGE = "https://jupyter.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=62f188c37957b44d9adbe64cb1932895"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "4b3f1b7dba531e498449c4db4f53da339c91d449dc11e9af3a43b4eb5c5abb09"

inherit pypi python_hatchling

RDEPENDS:${PN} = " \
	python3-jupyter-client \
	python3-jupyter-core \
	python3-nbformat \
	python3-traitlets \
	"

BBCLASSEXTEND = "native"
