#
# Copyright (C) 2024-2025, Advanced Micro Devices, Inc.  All rights reserved.
#
# SPDX-License-Identifier: MIT
#
# This bbclass is added to SDK_CLASSES variable to include qemu-xilinx nativesdk
# recipes for SDK(populate_sdk task) generation. User can generate the sdk script
# and install it to use runqemu, qemu-system-* binaries to boot qemu images.

AMD-EDF_QEMU_HOST_TASK = "\
    nativesdk-qemu \
    nativesdk-qemu-xilinx \
    nativesdk-qemu-xilinx-common \
    nativesdk-qemu-xilinx-multiarch-helper \
    nativesdk-qemu-helper \
    nativesdk-bootgen \
    nativesdk-qemu-devicetrees \
    nativesdk-qemuboot-tool \
"

AMD-EDF_QEMU_HOST_TASK:sdkmingw32 = ""

TOOLCHAIN_HOST_TASK += "${AMD-EDF_QEMU_HOST_TASK}"
