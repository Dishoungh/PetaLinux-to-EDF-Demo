#!/bin/bash
eeprom=$(ls /sys/bus/i2c/devices/*/eeprom_cc*/nvmem 2> /dev/null)
if [ -n "${eeprom}" ]; then
    boardid=$(ipmi-fru --fru-file=${eeprom} --interpret-oem-data | awk -F": " '/FRU Board Product/ { print tolower ($2) }')
    revision=$(ipmi-fru --fru-file=${eeprom} --interpret-oem-data | awk -F": " '/FRU Board Custom/ { print tolower ($2); exit }')

    #Check for board specific config with revision
    config="/usr/share/config/ser2net_${boardid}_${revision}.yaml"
    if [ -e $config ]; then
        ln -sf $config /etc/ser2net/ser2net.yaml
        exit 0
    fi

    #Check for board specific config without revision
    config="/usr/share/config/ser2net_${boardid}.yaml"
    if [ -e $config ]; then
        ln -sf $config /etc/ser2net/ser2net.yaml
        exit 0
    fi
fi
