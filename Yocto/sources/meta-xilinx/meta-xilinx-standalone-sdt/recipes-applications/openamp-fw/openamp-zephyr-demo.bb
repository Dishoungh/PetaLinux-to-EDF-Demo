require openamp-fw.inc
MCNAME = "zephyr"
FW_MCDEPENDS = "mc::versal-2ve-2vm-vek385-sdt-seg-cortexr52-0-zephyr:zephyr-openamp-rpmsg-multi-srv:do_deploy"
TARGET_MC = "cortexr52-0-zephyr"
OPENAMP_FW_NAME = "zephyr-openamp-rpmsg-multi-srv.elf"
OPENAMP_FW_SRC_NAME = "${OPENAMP_FW_NAME}"
OPENAMP_XLNX_RECIPE = "${OPENAMP_FW_NAME}"

PROVIDES:append:armv7r = " openamp-zephyr-demo "
PROVIDES:append:armv8r = " openamp-zephyr-demo "

RPROVIDES:${PN} += "openamp-zephyr-demo"
DEPENDS = ""
