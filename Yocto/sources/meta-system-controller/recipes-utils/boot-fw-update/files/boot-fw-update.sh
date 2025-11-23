#!/bin/bash

MOTD_FILE="/etc/motd"
MOTD_MARKER_START="### BOOT-fw update notification START ###"
MOTD_MARKER_END="### BOOT-fw update notification END ###"

get_hostname() {
    hostname | sed 's/\..*//g' | sed 's/-/_/g'
}

check_for_new_version() {
    local PACKAGE="xilinx-bootbin.$(get_hostname)"
    if dnf check-update $PACKAGE > /dev/null 2>&1; then
        update_motd_message $PACKAGE
    else
        cleanup_motd_message
    fi
}

update_motd_message() {
    local PACKAGE=$1
    local TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")
    local MESSAGE="************************************************************************\n\
* New firmware update available for BOOT.bin (Last checked: $TIMESTAMP)*\n\
* To update follow steps:                                              *\n\
* 1. sudo dnf install $PACKAGE                                         *\n\
* 2. Program the QSPI partition:                                       *\n\
*    image_update -p                                                   *\n\
*    image_update -i /boot/BOOT.bin                                    *\n\
**************************************************************************"

    sed -i "/$MOTD_MARKER_START/,/$MOTD_MARKER_END/d" "$MOTD_FILE"
    echo -e "$MOTD_MARKER_START\n$MESSAGE\n$MOTD_MARKER_END" >> "$MOTD_FILE"
}
cleanup_motd_message() {
    if grep -q "$MOTD_MARKER_START" "$MOTD_FILE"; then
        sed -i "/$MOTD_MARKER_START/,/$MOTD_MARKER_END/d" "$MOTD_FILE"
    fi
}                                                                     
check_for_new_version
