SUMMARY = "A tiny CSS parser"
HOMEPAGE = "https://www.courtbouillon.org/tinycss2"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1d072d7e30e34f33f8ae956ada04fa2c"

SRC_URI[sha256sum] = "152f9acabd296a8375fbca5b84c961ff95971fcfc32e79550c8df8e29118c54d"

inherit pypi python_flit_core

RDEPENDS:${PN} = "python3-webencodings"

BBCLASSEXTEND = "native"
