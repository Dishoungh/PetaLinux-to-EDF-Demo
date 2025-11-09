SUMMARY = "RFC 6570 URI Template Processor"
HOMEPAGE = "https://gitlab.linss.com/open-source/python/uri-template"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fa2ca59ca7680a47c8dc6c1b24758995"

DEPENDS += "python3-setuptools-scm-native"

SRC_URI[sha256sum] = "0e00f8eb65e18c7de20d595a14336e9f337ead580c70934141624b6d1ffdacc7"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native"
