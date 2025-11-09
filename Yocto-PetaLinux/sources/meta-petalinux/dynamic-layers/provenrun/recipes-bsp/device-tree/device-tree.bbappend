FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = "${@bb.utils.contains('MACHINE_FEATURES', 'provencore', ' file://pnc.dtsi', '', d)}"

EXTRA_DT_INCLUDE_FILES:append = "${@bb.utils.contains('MACHINE_FEATURES', 'provencore', ' pnc.dtsi', '', d)}"
