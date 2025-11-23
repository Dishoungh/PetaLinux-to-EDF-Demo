SUMMARY = "ipywidgets, also known as jupyter-widgets or simply widgets, are interactive HTML widgets for Jupyter notebooks and the IPython kernel."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=20a40995a0b2f0ae1f2a70d2dc995bbf"

SRC_URI[sha256sum] = "f5f9eeaae082b1823ce9eac2575272952f40d748893972956dc09700a6392d9c"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native"
