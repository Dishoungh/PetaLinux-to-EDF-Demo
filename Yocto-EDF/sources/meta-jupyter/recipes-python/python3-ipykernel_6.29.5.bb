SUMMARY = "IPython Kernel for Jupyter"
HOMEPAGE = "https://ipython.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=159b7ae0b4f7071ca9f0295354f6a49e"

DEPENDS = " \
	python3-hatchling-native \
	python3-jupyter-client-native \
	python3-jupyter-core-native \
	python3-dateutil-native \
	python3-pyzmq-native \
	python3-traitlets-native \
	zeromq-native \
	"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI[sha256sum] = "f093a22c4a40f8828f8e330a9c297cb93dcab13bd9678ded6de8e5cf81c56215"

inherit pypi python_hatchling

FILES:${PN} += "${datadir}"

RDEPENDS:${PN} = " \
	python3-comm \
	python3-debugpy \
	python3-ipython \
	python3-jupyter-client \
	python3-jupyter-core \
	python3-matplotlib-inline \
	python3-nest-asyncio \
	python3-packaging \
	python3-psutil \
	python3-pyzmq \
	python3-tornado \
	python3-traitlets \
	"

BBCLASSEXTEND = "native"
