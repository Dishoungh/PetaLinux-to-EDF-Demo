#!/bin/sh

# Copyright (C) 2024 Advanced Micro Devices, Inc. All rights reserved.
#
# SPDX-License-Identifier: MIT

if [ -e /sys/bus/platform/devices/rpu-channel/ready ]; then
   /usr/bin/skd
fi
