#! /bin/bash

#
# Copyright (c) 2024 Advanced Micro Devices, Inc.  All rights reserved.
#
# SPDX-License-Identifier: MIT
#
if [ -f /etc/profile.d/socks_proxy.sh ]; then
    . /etc/profile.d/socks_proxy.sh
fi

EEPROM=$(ls /sys/bus/i2c/devices/*/eeprom_cc*/nvmem 2> /dev/null)
BOARD=$(/usr/sbin/ipmi-fru --fru-file="$EEPROM" --interpret-oem-data | /usr/bin/awk -F": " '/FRU Board Product/ { print tolower ($2) }')
REVISION=$(/usr/sbin/ipmi-fru --fru-file="$EEPROM" --interpret-oem-data | /usr/bin/awk -F": " '/FRU Board Custom/ { print tolower ($2); exit }')


REV_LETTER="${REVISION:0:1}"
REV_NUMBER="${REVISION:1}"
PKG=""

# Detect if REV_NUMBER has leading zeros
if [[ "$REV_NUMBER" =~ ^0[0-9]+$ ]]; then
    PAD_FMT="%02d"
else
    PAD_FMT="%d"
fi

package_list=()

# Add all letter-number combos in descending order
REV_NUM_INT=$((10#$REV_NUMBER))  # Remove leading zeros for arithmetic
for ((i=REV_NUM_INT; i>0; i--)); do
    NUM=$(printf "$PAD_FMT" "$i")
    package_list+=("packagegroup-systemcontroller-${BOARD}-${REV_LETTER}${NUM}")
done

package_list+=("packagegroup-systemcontroller-${BOARD}")

# Find first matching package
for TRY_PKG in "${package_list[@]}"; do
    result=$(dnf repoquery "$TRY_PKG" 2>/dev/null)
    if [[ -n "$result" ]]; then
        PKG="$TRY_PKG"
        break
    fi
done


if [[ -z "$PKG" ]]; then
    echo "No matching package found for ${BOARD} revision ${REVISION}"
    exit 1
fi



echo Install board packages for "$BOARD"-"$REVISION"
if dnf install -y "${PKG}"; then
    echo "Install complete. Automatically rebooting in 5s."
    sleep 5 && reboot
else
    echo "Board install failed for ${BOARD}"
fi
