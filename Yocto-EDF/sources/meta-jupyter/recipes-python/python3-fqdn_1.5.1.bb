SUMMARY = "Validates fully-qualified domain names against RFC 1123, so that they are acceptable to modern bowsers"
HOMEPAGE = "https://github.com/ypcrts/fqdn"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=6a8fd56c1f0a09e0b442c90a5737b286"

SRC_URI[sha256sum] = "105ed3677e767fb5ca086a0c1f4bb66ebc3c100be518f0e0d755d9eae164d89f"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-core"

BBCLASSEXTEND = "native"
