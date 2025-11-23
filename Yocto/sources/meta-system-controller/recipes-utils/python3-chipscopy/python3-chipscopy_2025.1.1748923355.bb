SUMMARY = "Recipe to add 2025.1 ChipScopy Python Package"
LICENSE = "Apache-2.0 & EPL-2.0"
LIC_FILES_CHKSUM = "file://chipscopy-2025.1.1748923355.dist-info/LICENSE;md5=2d94686e8e79887c3661be21c344e542"

inherit  python3-dir

SRC_URI = "https://files.pythonhosted.org/packages/d9/d2/bed2bc89e40c9dfe22f6ddd9f5a15d4cc519e222e3d560b951ed20d7de91/chipscopy-2025.1.1748923355-py3-none-any.whl;downloadfilename=chipscopy-2025.1.1748923355-py3-none-any.zip;subdir=${BP}"


SRC_URI[md5sum] = "09143c34b1ce7b461e37297fa3a89b3a"
SRC_URI[sha256sum] = "1eb19a1c5b3c269197d346dd62edbf921cf7442e6f7099275f92501110191d48"

PN = "python3-chipscopy"

RDEPENDS:${PN} += " \
        ${PYTHON_PN}-click \
        ${PYTHON_PN}-importlib-metadata \
        ${PYTHON_PN}-loguru \
        ${PYTHON_PN}-more-itertools \
        ${PYTHON_PN}-rich \
        ${PYTHON_PN}-typing-extensions \
        ${PYTHON_PN}-pprint \
        ${PYTHON_PN}-json \
        ${PYTHON_PN}-matplotlib \
        ${PYTHON_PN}-plotly \
        ${PYTHON_PN}-regex \
        ${PYTHON_PN}-pandas \
        ${PYTHON_PN}-antlr4-runtime \
        "

DEPENDS += " \
	python3-wheel-native \
	python3-pip-native \
"

FILES:${PN} += "\
    ${libdir}/${PYTHON_DIR}/site-packages/* \
"

do_install() {
    install -d ${D}${libdir}/${PYTHON_DIR}/site-packages/chipscopy-2025.1.1748923355.dist-info
    install -d ${D}${libdir}/${PYTHON_DIR}/site-packages/chipscopy

    cp -r ${S}/chipscopy/* ${D}${libdir}/${PYTHON_DIR}/site-packages/chipscopy/
    cp -r ${S}/chipscopy-2025.1.1748923355.dist-info/* ${D}${libdir}/${PYTHON_DIR}/site-packages/chipscopy-2025.1.1748923355.dist-info/
}
