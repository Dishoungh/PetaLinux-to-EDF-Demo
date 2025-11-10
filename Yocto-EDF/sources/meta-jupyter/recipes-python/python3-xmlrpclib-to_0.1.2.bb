SUMMARY = "XMLRPC Client with timeout"
HOMEPAGE = "http://github.com/gisce/xmlrpclib-to"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=8227180126797a0148f94f483f3e1489"

SRC_URI[sha256sum] = "c371e7b98f5c3b22cc4b598f508c464239e6f4af827c4a86843369148700d751"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
	python3-core \
	python3-io \
	python3-netclient \
	python3-xmlrpc \
	"

BBCLASSEXTEND = "native"
