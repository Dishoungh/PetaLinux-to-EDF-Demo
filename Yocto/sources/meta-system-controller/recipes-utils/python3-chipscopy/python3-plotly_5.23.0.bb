SUMMARY = "plotly.py is an interactive, open-source, and browser-based graphing library for Python :sparkles:"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=c7b311a6fbf8f1e2f22c16e2ad556f98"

SRC_URI[sha256sum] = "89e57d003a116303a34de6700862391367dd564222ab71f8531df70279fc0193"

inherit pypi setuptools3

FILES:${PN} += "${prefix}"
