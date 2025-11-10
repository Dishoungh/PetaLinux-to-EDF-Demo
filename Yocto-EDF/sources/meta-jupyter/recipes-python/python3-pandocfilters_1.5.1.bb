SUMMARY = "Utilities for writing pandoc filters in python"
HOMEPAGE = "http://github.com/jgm/pandocfilters"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=28b59ac864caa776edcbdb77a8a57267"

SRC_URI[sha256sum] = "002b4a555ee4ebc03f8b66307e287fa492e4a77b4ea14d3f934328297bb4939e"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
	python3-core \
	python3-crypt \
	python3-io \
	python3-json \
	"

BBCLASSEXTEND = "native"
