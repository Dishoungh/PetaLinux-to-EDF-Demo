DESCRIPTION = "AMD Embedded Development Framework packages for native SDK"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup
inherit_defer nativesdk

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

AMD-EDF_NATIVESDK_PACKAGES = " \
	bmaptool \
        flashstrip \
	wic \
	"

RDEPENDS:${PN} = "${AMD-EDF_NATIVESDK_PACKAGES}"
