SUMMARY = "A sane and fast Markdown parser with useful plugins and renderers"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7662a489238001edfceff24a3ce11cbd"

SRC_URI[sha256sum] = "fc7f93ded930c92394ef2cb6f04a8aabab4117a91449e72dcc8dfa646a508be8"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native"
