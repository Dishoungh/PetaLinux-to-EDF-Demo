SUMMARY = "Python client for the Prometheus monitoring system."
HOMEPAGE = "https://github.com/prometheus/client_python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI[sha256sum] = "287629d00b147a32dcb2be0b9df905da599b2d82f80377083ec8463309a4bb89"

PYPI_PACKAGE = "prometheus_client"
inherit pypi setuptools3

PACKAGECONFIG ?= "twisted"
PACKAGECONFIG[twisted] = ",,,python3-twisted"

RDEPENDS:${PN} += " \
	python3-compression \
	python3-core \
	python3-io \
	python3-json \
	python3-logging \
	python3-misc \
	python3-mmap \
	python3-netclient \
	python3-netserver \
	python3-resource \
	python3-shell \
	"

BBCLASSEXTEND = "native"
