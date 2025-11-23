require open-amp-xlnx-demos_v2025.2.inc

PROVIDES += "open-amp-xlnx-matrix"

OPENAMP_APP_NAME = "matrix_multiply"

RPROVIDES:${PN}-dbg += "open-amp-xlnx-matrix-dbg"
RPROVIDES:${PN}-dev += "open-amp-xlnx-matrix-dev"
RPROVIDES:${PN}-lic += "open-amp-xlnx-matrix-lic"
RPROVIDES:${PN}-src += "open-amp-xlnx-matrix-src"
RPROVIDES:${PN}-staticdev += "open-amp-xlnx-matrix-staticdev"
