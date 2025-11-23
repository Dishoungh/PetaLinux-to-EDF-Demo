require open-amp-xlnx-demos_v2025.2.inc

PROVIDES += "open-amp-xlnx-proxy"

OPENAMP_APP_NAME = "rpc_demo"

RPROVIDES:${PN}-dbg += "open-amp-xlnx-proxy-dbg"
RPROVIDES:${PN}-dev += "open-amp-xlnx-proxy-dev"
RPROVIDES:${PN}-lic += "open-amp-xlnx-proxy-lic"
RPROVIDES:${PN}-src += "open-amp-xlnx-proxy-src"
RPROVIDES:${PN}-staticdev += "open-amp-xlnx-proxy-staticdev"
