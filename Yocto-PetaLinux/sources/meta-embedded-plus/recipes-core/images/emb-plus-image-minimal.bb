DESCRIPTION = "A minimal image for Embedded Plus."

inherit core-image

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:emb-plus-ve2302-xrt = "${MACHINE}"
COMPATIBLE_MACHINE:emb-plus-ve2302-amr = "${MACHINE}"

IMAGE_INSTALL = " \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    packagegroup-core-boot \
    kernel-modules \
    linux-xlnx-udev-rules \
    mtd-utils \
    pciutils \
    run-postinsts \
    udev-extraconf \
    lrzsz \
    iperf3 \
    netperf \
    ethtool \
    phytool \
    tcpdump \
    util-linux \
    libgpiod \
    libgpiod-tools \
    i2c-tools \
"

XRT_INSTALL = " \
     xrt \
     zocl \
     apu-boot \
     init-apu \
     soft-kernel-daemon \
"
IMAGE_INSTALL:append:emb-plus-ve2302-xrt = " ${XRT_INSTALL}"
