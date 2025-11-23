SUMMARY = "Genshi is a Python library that provides an integrated set of components for parsing, generating, and processing HTML, XML or other textual content for output generation on the web."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=22774fa7cdae63f23b38015851ba1938"

SRC_URI[sha256sum] = "c76170a8b2dc18944e0915103c284cb889dfcee34e0e140ba3363c80f7541ad2"

PYPI_PACKAGE = "Genshi"
inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native"
