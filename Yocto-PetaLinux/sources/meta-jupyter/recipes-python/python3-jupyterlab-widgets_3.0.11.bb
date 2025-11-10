SUMMARY = "Jupyter interactive widgets for JupyterLab"
HOMEPAGE = "https://github.com/jupyter-widgets/ipywidgets"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=46c4b25eb11307c56dc6205528a80fb0"

DEPENDS += " \
	python3-jupyter-packaging-native \
	python3-tomlkit-native \
	"

SRC_URI[sha256sum] = "dd5ac679593c969af29c9bed054c24f26842baa51352114736756bc035deee27"

PYPI_PACKAGE = "jupyterlab_widgets"
inherit pypi setuptools3

FILES:${PN} += "${prefix}"

RDEPENDS:${PN} += "python3-core"

BBCLASSEXTEND = "native"
