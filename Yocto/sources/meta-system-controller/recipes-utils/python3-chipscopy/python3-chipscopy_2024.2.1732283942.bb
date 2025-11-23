SUMMARY = "Recipe to add 2024.2 ChipScopy Python Package"
LICENSE = "Apache-2.0 & EPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2d94686e8e79887c3661be21c344e542 \
                    file://epl-v20.html;md5=84283fa8859daf213bdda5a9f8d1be1d \
"

inherit  python3-dir

SRC_URI = "https://files.pythonhosted.org/packages/7f/f3/b78243090c21cf0f5fff59f034ae65425e1f1d6b0f25d27bd1ba26af6977/chipscopy-2024.2.1732283942-py3-none-any.whl;downloadfilename=chipscopy-2024.2.1732283942-py3-none-any.zip;subdir=${BP}"


SRC_URI[md5sum] = "ee7ce26b781f3a973eb2ad3ecea028bb"
SRC_URI[sha256sum] = "9fe73c783f8e503cebbab277f13ddde7f5f6bad8b48bc3801223a856cec699b2"

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
    install -d ${D}${libdir}/${PYTHON_DIR}/site-packages/chipscopy-2024.2.1732283942.dist-info
    install -d ${D}${libdir}/${PYTHON_DIR}/site-packages/chipscopy

    cp -r ${S}/chipscopy/* ${D}${libdir}/${PYTHON_DIR}/site-packages/chipscopy/
    cp -r ${S}/chipscopy-2024.2.1732283942.dist-info/* ${D}${libdir}/${PYTHON_DIR}/site-packages/chipscopy-2024.2.1732283942.dist-info/
}
