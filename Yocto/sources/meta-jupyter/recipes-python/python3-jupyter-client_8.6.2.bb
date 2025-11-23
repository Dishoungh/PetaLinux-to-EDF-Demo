SUMMARY = "Jupyter protocol implementation and client libraries"
HOMEPAGE = "https://jupyter.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=083556a9912a35360dae8281fb57e886"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "2bda14d55ee5ba58552a8c53ae43d215ad9868853489213f37da060ced54d8df"

PYPI_PACKAGE = "jupyter_client"
inherit pypi python_hatchling

RDEPENDS:${PN} = " \
	python3-dateutil \
	python3-core \
	python3-importlib-metadata \
	python3-jupyter-client \
	python3-pyzmq \
	python3-tornado \
	python3-traitlets \
	"

BBCLASSEXTEND = "native"
