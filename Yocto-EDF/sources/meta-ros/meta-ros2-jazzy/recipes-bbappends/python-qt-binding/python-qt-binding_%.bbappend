# Copyright (c) 2023 Wind River Systems, Inc.
# Copyright (C) 2024 Advanced Micro Devices, Inc.  All rights reserved.

LICENSE = "BSD-3-Clause"

# Disable buildpaths QA check warnings.
INSANE_SKIP:${PN} += "buildpaths"
