SUMMARY = "QtPy is a small abstraction layer that lets you write applications using a single API call to either PyQt or PySide."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b2830f54500be1314b9ec6096989f983"

SRC_URI[sha256sum] = "a5a15ffd519550a1361bdc56ffc07fda56a6af7292f17c7b395d4083af632987"

PYPI_PACKAGE = "QtPy"
inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native"
