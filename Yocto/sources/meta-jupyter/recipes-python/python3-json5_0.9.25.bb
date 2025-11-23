SUMMARY = "A Python implementation of the JSON5 data format."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=18413e679155848797966e3c2319a9ea"

SRC_URI[sha256sum] = "548e41b9be043f9426776f05df8635a00fe06104ea51ed24b67f908856e151ae"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native"
