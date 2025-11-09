SUMMARY = "A python library adding a json log formatter"
HOMEPAGE = "http://github.com/madzak/python-json-logger"
LICENSE = "BSD-2-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c51b08a7d58875addc0d97d4d985c905"

SRC_URI[sha256sum] = "23e7ec02d34237c5aa1e29a070193a4ea87583bb4e7f8fd06d3de8264c4b2e1c"

PYPI_PACKAGE = "python-json-logger"
inherit pypi setuptools3

RDEPENDS:${PN} += " \
	python3-core \
	python3-datetime \
	python3-json \
	python3-logging \
	python3-math \
	python3-unittest \
	"

BBCLASSEXTEND = "native"
