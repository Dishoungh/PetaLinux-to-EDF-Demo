LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=767eeb0122cccaf224035860df064532"

DEPENDS += " \
	python3-setuptools-scm-native \
	"

SRC_URI[sha256sum] = "6f172d5449aca15afd6c646851f4e31e02c598d553a667e38cafa997cfec55fe"

PYPI_PACKAGE = "nest_asyncio"
inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native"
