DESCRIPTION = "TSN packages"

inherit packagegroup

TSN_PACKAGES = " \
	gptp \
	linuxptp \
	lldpd \
	tsn-examples \
	tsn-utils \
	vlan \
	"

RDEPENDS:${PN} = "${TSN_PACKAGES}"
