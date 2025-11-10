SUMMARY = "debugpy is an implementation of the Debug Adapter Protocol for Python."
HOMEPAGE = "https://pypi.org/project/debugpy/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7b6b095fe2a2e2b66cb08d295b605789"

SRC_URI[sha256sum] = "b2112cfeb34b4507399d298fe7023a16656fc553ed5246536060ca7bd0e668d0"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE_EXT = "zip"

BBCLASSEXTEND = "native"
