# Copyright (c) 2019 LG Electronics, Inc.
# Copyright (c) 2023 Wind River Systems, Inc.
# Copyright (C) 2024 Advanced Micro Devices, Inc.  All rights reserved.

ROS_BUILDTOOL_DEPENDS += " \
    python3-empy-native \
    python3-numpy-native \
    rcutils-native \
"

# Disable buildpaths QA check warnings.
INSANE_SKIP:${PN} += "buildpaths"
