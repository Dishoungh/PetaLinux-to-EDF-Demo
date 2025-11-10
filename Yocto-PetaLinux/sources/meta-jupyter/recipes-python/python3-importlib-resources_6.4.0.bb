SUMMARY = "The key goal of this module is to replace parts of pkg_resources with a solution in Python’s stdlib that relies on well-defined APIs."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS += "python3-setuptools-scm-native"

SRC_URI[sha256sum] = "cdb2b453b8046ca4e3798eb1d84f3cce1446a0e8e7b5ef4efb600f19fc398145"

PYPI_PACKAGE = "importlib_resources"
inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native"
