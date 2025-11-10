SUMMARY = "A set of server components for JupyterLab and JupyterLab like applications."
HOMEPAGE = "https://jupyterlab-server.readthedocs.io"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=16b24abb4aef09551533365c88c785cf"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "eb36caca59e74471988f0ae25c77945610b887f777255aa21f8065def9e51ed4"

PYPI_PACKAGE = "jupyterlab_server"
inherit pypi python_hatchling

RDEPENDS:${PN} = " \
	python3-babel \
	python3-importlib-metadata \
	python3-jinja2 \
	python3-json5 \
	python3-jsonschema \
	python3-jupyter-server \
	python3-packaging \
	python3-requests \
	"

BBCLASSEXTEND = "native"
