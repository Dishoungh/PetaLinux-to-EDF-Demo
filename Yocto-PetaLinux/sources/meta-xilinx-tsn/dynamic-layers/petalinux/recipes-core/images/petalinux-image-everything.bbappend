require petalinux-image-common-tsn.inc

# Install tsn packages
IMAGE_INSTALL:append:zynqmp = " packagegroup-tsn"
