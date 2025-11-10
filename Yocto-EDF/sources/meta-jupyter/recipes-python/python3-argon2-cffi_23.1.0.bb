SUMMARY = "Argon2 won the Password Hashing Competition and argon2-cffi is the simplest way to use it in Python."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e91e96ef55f45fe9caf7fc3e73672c4b"

DEPENDS += " \
	python3-hatch-vcs-native \
	python3-hatch-fancy-pypi-readme-native \
	"

SRC_URI[sha256sum] = "879c3e79a2729ce768ebb7d36d4609e3a78a4ca2ec3a9f12286ca057e3d0db08"

PYPI_PACKAGE = "argon2_cffi"
inherit pypi python_hatchling

BBCLASSEXTEND = "native"
