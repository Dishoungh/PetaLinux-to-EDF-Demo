SUMMARY = "Hatch plugin for versioning from a package.json file"
HOMEPAGE = "https://github.com/agoose77/hatch-nodejs-version"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f35b870d1270c398ee82da12ae79578a"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "8a7828d817b71e50bbbbb01c9bfc0b329657b7900c56846489b9c958de15b54c"

PYPI_PACKAGE = "hatch_nodejs_version"
inherit pypi python_hatchling

RDEPENDS:${PN} = "python3-hatchling"

BBCLASSEXTEND = "native"
