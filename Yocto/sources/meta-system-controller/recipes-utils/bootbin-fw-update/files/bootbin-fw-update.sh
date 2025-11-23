#!/bin/bash

MOTD_FILE="/etc/motd"
MOTD_MARKER_START="### BOOT-FW Update Notification START ###"
MOTD_MARKER_END="### BOOT-FW Update Notification END ###"
BOOT_BIN_FILE="/boot/BOOT.bin"


update_motd_message() {
    local BANK=$1
    local MESSAGE="***************************************************************************************\n\
*                                                                                     *\n\
*                           BOOT-FW HAS BEEN UPDATED TO $BANK                        *\n\
*                                                                                     *\n\
*    Follow the steps below to make the updated BOOT-FW active:                       *\n\
*                                                                                     *\n\
* 1. Reboot the system to boot the updated BootFW image.                              *\n\
* 2. Mark the BootFW image as bootable using image_update -v after a successful boot. *\n\
*                                                                                     *\n\
***************************************************************************************"

    sed -i "/$MOTD_MARKER_START/,/$MOTD_MARKER_END/d" "$MOTD_FILE"
    echo -e "$MOTD_MARKER_START\n$MESSAGE\n$MOTD_MARKER_END" >> "$MOTD_FILE"
}


cleanup_motd_message() {
    if grep -q "$MOTD_MARKER_START" "$MOTD_FILE"; then
        sed -i "/$MOTD_MARKER_START/,/$MOTD_MARKER_END/d" "$MOTD_FILE"
    fi
}
update_version() {
    local UPDATE_OUT=$(image_update -i $BOOT_BIN_FILE)
    local UPDATED_BANK=$(echo "$UPDATE_OUT" | grep "$BOOT_BIN_FILE successfully updated" | awk '{print $5}')
    update_motd_message $UPDATED_BANK
}

check_version() {
    local ERROR_FLAG=0
    local PACKAGE=$(rpm -q --whatprovides $BOOT_BIN_FILE)
    local RPM_BOOT_BIN_VERSION=$(echo "$PACKAGE" | cut -d'-' -f3)
    local PER_REG_OUTPUT=$(image_update -p)
    local LAST_BOOTED_IMAGE=$(echo "$PER_REG_OUTPUT" | grep "Last Booted Image:" | awk '{print $NF}')
    local CURRENT_BOOT_BIN_VERSION=""
    if [[ "$LAST_BOOTED_IMAGE" == "A" ]]; then
        CURRENT_BOOT_BIN_VERSION=$(echo "$PER_REG_OUTPUT" | grep "ImageA Revision Info:" | cut -d'-' -f4 | sed 's/^v//')
    elif [[ "$LAST_BOOTED_IMAGE" == "B" ]]; then
        CURRENT_BOOT_BIN_VERSION=$(echo "$PER_REG_OUTPUT" | grep "ImageB Revision Info:" | cut -d'-' -f4 | sed 's/^v//')
    else
        ERROR_FLAG=1
    fi
    if [[ $ERROR_FLAG == 0 ]]; then
        if awk -v n1="$RPM_BOOT_BIN_VERSION" -v n2="$CURRENT_BOOT_BIN_VERSION" '
        BEGIN {
            exit !(n1 > n2)
        }'; then
            update_version
        else
            cleanup_motd_message
        fi
    else
        cleanup_motd_message
    fi
}

if [[ -f "$BOOT_BIN_FILE" ]]; then
    check_version
else
    cleanup_motd_message
fi
