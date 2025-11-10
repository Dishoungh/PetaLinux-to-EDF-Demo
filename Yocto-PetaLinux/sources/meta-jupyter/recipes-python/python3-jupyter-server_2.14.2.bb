SUMMARY = "The backend—i.e. core services, APIs, and REST endpoints—to Jupyter web applications."
HOMEPAGE = "https://jupyter-server.readthedocs.io"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=083556a9912a35360dae8281fb57e886"

DEPENDS = " \
	python3-hatchling-native \
	python3-hatch-jupyter-builder-native \
	"

SRC_URI[sha256sum] = "66095021aa9638ced276c248b1d81862e4c50f292d575920bbe960de1c56b12b"

PYPI_PACKAGE = "jupyter_server"
inherit pypi python_hatchling

RDEPENDS:${PN} = " \
	python3-anyio \
	python3-argon2-cffi \
	python3-jinja2 \
	python3-jupyter-client \
	python3-jupyter-core \
	python3-jupyter-events \
	python3-jupyter-server-terminals \
	python3-nbconvert \
	python3-nbformat \
	python3-overrides \
	python3-packaging \
	python3-prometheus-client \
	python3-pyzmq \
	python3-send2trash \
	python3-terminado \
	python3-tornado \
	python3-traitlets \
	python3-websocket-client \
	"

BBCLASSEXTEND = "native"
