DESCRIPTION = "Common packages for the AMD Embedded Development Framework distro and images"
LICENSE = "MIT"

# By default wic is not enable in IMAGE_FSTYPES so enable WIC image type support.
IMAGE_FSTYPES += "wic wic.xz wic.bmap"

#### Disable Busybox
SKIP_RECIPE[busybox] = "Busybox disabled"

# Install license files into the image by default
IMAGE_FEATURES .= "${@' lic-pkgs' if oe.data.typed_value('LICENSE_CREATE_PACKAGE', d) else ''}"

# Default we use the fpga-overlay to select dfx-mgr
AMD-EDF_FPGA_OVERLAY_INSTALL = "${@bb.utils.contains('MACHINE_FEATURES', 'fpga-overlay', 'dfx-mgr', '', d)}"

# Eliminate architectures where this is not supported
AMD-EDF_FPGA_OVERLAY_INSTALL:riscv32 = ""
AMD-EDF_FPGA_OVERLAY_INSTALL:riscv64 = ""
AMD-EDF_FPGA_OVERLAY_INSTALL:arm     = ""

# Minimal set packages for EDF images
AMD-EDF_IMAGE_COMMON_INSTALL = "\
    ${AMD-EDF_FPGA_OVERLAY_INSTALL} \
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
    yavta \
    ${AMD-EDF_IMAGE_COMMON_INSTALL} \
    can-utils \
    container-host-config \
    dosfstools \
    dtc \
    ethtool \
    hexedit \
    htop \
    iperf3 \
    linuxptp \
    net-tools \
    phytool \
    python3-gpiod \
    python3-pip \
    systemd-conf-edf \
    tcf-agent \
    ufs-utils \
    util-linux-taskset \
    vim \
    "

# COMMON is for anything in that family with no board specific items
# w/o COMMON is for _JUST_ that type
AMD_RISCV32_COMMON_INSTALL = "linux-xlnx-udev-rules"
AMD_RISCV32_INSTALL = ""

AMD_RISCV64_COMMON_INSTALL = "linux-xlnx-udev-rules"
AMD_RISCV64_INSTALL = ""

AMD_CORTEXA9_COMMON_INSTALL = " \
    linux-xlnx-udev-rules \
    packagegroup-core-sdk packagegroup-core-standalone-sdk-target \
    "

AMD_CORTEXA9_INSTALL = " \
    zc702-pl-bram-gpio-fw \
"

AMD_CORTEXA53_COMMON_INSTALL = "\
    docker-moby-contrib \
    libdrm \
    libdrm-tests \
    linux-xlnx-udev-rules \
    packagegroup-xilinx-audio \
    packagegroup-xilinx-gstreamer \
    packagegroup-openamp \
    packagegroup-oci \
    packagegroup-docker \
    packagegroup-opencv \
    systemtap \
    v4l-utils \
    xrt \
    packagegroup-core-sdk packagegroup-core-standalone-sdk-target \
    fwupd-quirks \
    "

AMD_CORTEXA53_INSTALL = "\
    zcu111-pl-bram-gpio-fw \
    zcu102-pl-bram-gpio-fw \
    "

AMD_CORTEXA53_MALI_COMMON_INSTALL = "\
    ${AMD_CORTEXA53_COMMON_INSTALL} \
    vcu-ctrlsw \
    ${@bb.utils.contains('DISTRO_FEATURES', 'libmali', ' libmali-xlnx', '', d)} \
    "

AMD_CORTEXA53_MALI_INSTALL = "\
    zcu104-pl-bram-gpio-fw \
    zcu106-pl-bram-gpio-fw \
    "

AMD_CORTEXA72_COMMON_INSTALL = "\
    ${AMD_CORTEXA53_COMMON_INSTALL} \
    vdu-ctrlsw \
    ${@bb.utils.contains('DISTRO_FEATURES', 'xen', ' xen-zephyr-domu-image', '', d)} \
    "

AMD_CORTEXA72_INSTALL = "\
    vek280-pl-bram-gpio-fw \
    vck190-pl-bram-gpio-fw \
    vmk180-pl-bram-gpio-fw \
    vpk120-pl-bram-gpio-fw \
    vrk160-pl-bram-gpio-fw \
    vck190-openamp-fw-examples \
    vek280-openamp-fw-examples \
    vmk180-openamp-fw-examples \
    vpk120-openamp-fw-examples \
    vrk160-openamp-fw-examples \
    "

AMD_CORTEXA78_COMMON_INSTALL = "\
    ${AMD_CORTEXA53_COMMON_INSTALL} \
    "

AMD_CORTEXA78_INSTALL = ""

AMD_CORTEXA78_MALI_COMMON_INSTALL = "\
    ${AMD_CORTEXA78_COMMON_INSTALL} \
    isp-media-server \
    isp-firmware \
    kernel-module-isp \
    vcu2-ctrlsw \
    ${@bb.utils.contains('DISTRO_FEATURES', 'libmali', ' libmali-xlnx', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee', ' optee-client', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'xen', ' xen-zephyr-domu-image', '', d)} \
    "

AMD_CORTEXA78_MALI_INSTALL = "\
    vek385-pl-bram-gpio-fw \
    vek385-revb-pl-bram-gpio-fw \
    vek385-openamp-fw-examples \
    vek385-revb-openamp-fw-examples \
    "

AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-rv32imac-zicbom-zba-zbb-zbs-common = " ${AMD_RISCV32_COMMON_INSTALL} ${AMD_RISCV32_INSTALL}"
AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-rv64imafdc-zicbom-zba-zbb-zbs-common = " ${AMD_RISCV64_COMMON_INSTALL} ${AMD_RISCV64_INSTALL}"
AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-cortexa9thf-neon-common = " ${AMD_CORTEXA9_COMMON_INSTALL} ${AMD_CORTEXA9_INSTALL}"
AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-cortexa53-common = " ${AMD_CORTEXA53_COMMON_INSTALL} ${AMD_CORTEXA53_INSTALL}"
AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-cortexa53-mali-common = " ${AMD_CORTEXA53_MALI_COMMON_INSTALL} ${AMD_CORTEXA53_MALI_INSTALL}"
AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-cortexa72-common = " ${AMD_CORTEXA72_COMMON_INSTALL} ${AMD_CORTEXA72_INSTALL}"
AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-cortexa78-common = " ${AMD_CORTEXA78_COMMON_INSTALL} ${AMD_CORTEXA78_INSTALL}"
AMD-EDF_IMAGE_COMMON_INSTALL:append:amd-cortexa78-mali-common = " ${AMD_CORTEXA78_MALI_COMMON_INSTALL} ${AMD_CORTEXA78_MALI_INSTALL}"
