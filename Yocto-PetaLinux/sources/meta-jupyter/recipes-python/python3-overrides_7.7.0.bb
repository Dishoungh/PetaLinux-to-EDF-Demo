SUMMARY = "A decorator to automatically detect mismatch when overriding a method."
HOMEPAGE = "https://github.com/mkorpela/overrides"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fa818a259cbed7ce8bc2a22d35a464fc"

SRC_URI[sha256sum] = "55158fa3d93b98cc75299b1e67078ad9003ca27945c76162c1c0766d6f91820a"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-core"

BBCLASSEXTEND = "native"
