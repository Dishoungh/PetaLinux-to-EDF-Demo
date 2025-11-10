SUMMARY = "JupyterLab computational environment"
HOMEPAGE = "https://jupyter.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3833c7b0556c7185b65b20af0b237965"

DEPENDS = " \
	python3-hatchling-native \
	python3-hatch-jupyter-builder-native \
	"

SRC_URI[sha256sum] = "343a979fb9582fd08c8511823e320703281cd072a0049bcdafdc7afeda7f2537"

inherit pypi python_hatchling mime-xdg

FILES:${PN} += " \
	${datadir} \
	${prefix}/etc/jupyter \
	"

RDEPENDS:${PN} = " \
	python3-async-lru \
	python3-defusedxml \
	python3-importlib-metadata \
	python3-importlib-resources \
	python3-ipykernel \
	python3-ipywidgets \
	python3-httpx \
	python3-jinja2 \
	python3-jupyter-console \
	python3-jupyter-core \
	python3-jupyter-lsp \
	python3-jupyter-server \
	python3-jupyterlab-pygments \
	python3-jupyterlab-server \
	python3-jupyterlab-widgets \
	python3-notebook-shim \
	python3-packaging \
	python3-qtconsole \
	python3-setuptools \
	python3-tomli \
	python3-tornado \
	python3-traitlets \
	python3-xmlrpclib-to \
	"

BBCLASSEXTEND = "native"
