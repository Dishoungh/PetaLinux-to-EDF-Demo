DESCRIPTION = "system controller scweb app packages"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Need to update PR, PACKAGE_ARCH changed.
PR = "r1"

inherit packagegroup

# Packages
SYS_CONTROLLER_SCWEB_PACKAGES = " \
        python3 \
        python3-flask \
        python3-flask-restful \
        packagegroup-syscontroller \
        pmtool \
        scweb \
       "

RDEPENDS:${PN} = "${SYS_CONTROLLER_SCWEB_PACKAGES}"
