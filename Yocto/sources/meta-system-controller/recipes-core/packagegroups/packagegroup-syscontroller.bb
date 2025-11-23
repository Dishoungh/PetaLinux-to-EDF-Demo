DESCRIPTION = "Required packages for system controller"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Need to update PR, PACKAGE_ARCH changed.
PR = "r1"

inherit packagegroup

SYSTEM_CONTROLLER_PACKAGES = " \
        python3-flask \
        python3-flask-restful \
        python3-werkzeug \
        python3-jinja2 \
        python3-markupsafe \
        python3-itsdangerous \
        python3-twisted \
        python3-gevent \
        python3-matplotlib \
        packagegroup-lmsensors \
        i2c-tools \
        libgpiod \
        libgpiod-tools \
        system-controller-app \
        python3-loguru \
        python3-rich \
        python3-chipscopy \
        lmsensors-sensors \
        lmsensors-libsensors \
        lmsensors-sensorsdetect \
        repart-resize \
        sc-usb-gadget \
        "

RDEPENDS:${PN} = "${SYSTEM_CONTROLLER_PACKAGES}"

SYSTEM_CONTROLLER_PACKAGES:append:vck-sc-zynqmp = " \
        packagegroup-systemcontroller-vck190 \
        packagegroup-systemcontroller-vmk180 \
        "
