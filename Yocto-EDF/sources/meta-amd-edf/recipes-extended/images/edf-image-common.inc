DESCRIPTION = "Common packages for the AMD Embedded Development Framework distro and images"
LICENSE = "MIT"

# By default wic is not enable in IMAGE_FSTYPES so enable WIC image type support.
IMAGE_FSTYPES += "wic wic.xz wic.bmap"

#### Disable Busybox
SKIP_RECIPE[busybox] = "Busybox disabled"

# Install license files into the image by default
IMAGE_FEATURES .= "${@' lic-pkgs' if oe.data.typed_value('LICENSE_CREATE_PACKAGE', d) else ''}"

# Minimal set packages for EDF images
AMD-EDF_IMAGE_COMMON_INSTALL = "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'fpga-overlay', ' dfx-mgr', '', d)} \
    libgpiod \
    libgpiod-tools \
    devmem2 \
    kernel-modules \
    mtd-utils \
    fwupd \
    i2c-tools \
    pciutils \
    bmaptool \
    usbutils \
    fpga-manager-script \
    "

# Full set of packages for EDF images
AMD-EDF_IMAGE_FULL_INSTALL = " \
    tree \
    libdrm \
    libdrm-tests \
    packagegroup-xilinx-audio \
    packagegroup-xilinx-gstreamer \
    v4l-utils \
    yavta \
    ${AMD-EDF_IMAGE_COMMON_INSTALL} \
    can-utils \
    container-host-config \
    docker-moby-contrib \
    dosfstools \
    dtc \
    ethtool \
    hexedit \
    htop \
    iperf3 \
    linux-xlnx-udev-rules \
    linuxptp \
    net-tools \
    packagegroup-docker \
    packagegroup-oci \
    phytool \
    python3-gpiod \
    python3-pip \
    systemtap \
    tcf-agent \
    ufs-utils \
    util-linux-taskset \
    vim \
    xrt \
    packagegroup-openamp \
    "

AMD_CORTEXA53_COMMON_INSTALL = "\
    zcu111-pl-bram-gpio-fw \
    "

AMD_CORTEXA53_MALI_COMMON_INSTALL = "\
    vcu-ctrlsw \
    ${@bb.utils.contains('DISTRO_FEATURES', 'libmali', ' libmali-xlnx', '', d)} \
    "

AMD_CORTEXA72_COMMON_INSTALL = "\
    vdu-ctrlsw \
    vek280-pl-bram-uart-gpio-fw \
    vck190-pl-bram-gpio-fw \
    vrk160-pl-bram-gpio-fw \
    "

AMD_CORTEXA78_COMMON_INSTALL = ""

AMD_CORTEXA78_MALI_COMMON_INSTALL = "\
    isp-firmware \
    kernel-module-isp \
    vcu2-ctrlsw \
    ${@bb.utils.contains('DISTRO_FEATURES', 'libmali', ' libmali-xlnx', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee', ' optee-client', '', d)} \
    "

AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-cortexa53-common = " ${AMD_CORTEXA53_COMMON_INSTALL}"
AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-cortexa53-mali-common = " ${AMD_CORTEXA53_MALI_COMMON_INSTALL}"
AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-cortexa72-common = " ${AMD_CORTEXA72_COMMON_INSTALL}"
AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-cortexa78-common = " ${AMD_CORTEXA78_COMMON_INSTALL}"
AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-cortexa78-mali-common = " ${AMD_CORTEXA78_MALI_COMMON_INSTALL}"
