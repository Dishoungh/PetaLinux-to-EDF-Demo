SUMMARY = "Jupyter Event System library"
HOMEPAGE = "http://jupyter.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=469c300e985ff0b264c3ba21b4fae725"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "670b8229d3cc882ec782144ed22e0d29e1c2d639263f92ca8383e66682845e22"

PYPI_PACKAGE = "jupyter_events"
inherit pypi python_hatchling

RDEPENDS:${PN} = " \
	python3-json-logger \
	python3-jsonschema \
	python3-pyyaml \
	python3-referencing \
	python3-traitlets \
	python3-rfc3339-validator \
	python3-rfc3986-validator \
	"

BBCLASSEXTEND = "native"
