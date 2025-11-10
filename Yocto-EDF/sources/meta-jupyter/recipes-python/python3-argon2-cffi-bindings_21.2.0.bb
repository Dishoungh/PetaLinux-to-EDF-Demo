SUMMARY = "argon2-cffi-bindings provides low-level CFFI bindings to the Argon2 password hashing algorithm including a vendored version of them."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4642dfcbd13c1cc49e9f99df9de51ba1"

DEPENDS = " \
	python3-cffi-native \
	python3-setuptools-scm-native \
	"

SRC_URI[sha256sum] = "bb89ceffa6c791807d1305ceb77dbfacc5aa499891d2c55661c6459651fc39e3"

export ARGON2_CFFI_USE_SSE2 = "0"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native"
