SUMMARY = "Jupyter Qt console"
HOMEPAGE = "http://jupyter.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0b84758cc535b6c0e177dbda70ee3207"

SRC_URI[sha256sum] = "6b5fb11274b297463706af84dcbbd5c92273b1f619e6d25d08874b0a88516989"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
	python3-ipykernel \
	python3-jupyter-client \
	python3-jupyter-core \
	python3-packaging \
	python3-pygments \
	python3-pyzmq \
	python3-qtpy \
	python3-traitlets \
	python3-asyncio \
	python3-core \
	python3-ctypes \
	python3-image \
	python3-io \
	python3-ipykernel \
	python3-ipython \
	python3-jupyter-client \
	python3-jupyter-core \
	python3-logging \
	python3-misc \
	python3-netclient \
	python3-packaging \
	python3-pygments \
	python3-threading \
	python3-traitlets \
	python3-unittest \
	python3-xml \
	"

BBCLASSEXTEND = "native"
