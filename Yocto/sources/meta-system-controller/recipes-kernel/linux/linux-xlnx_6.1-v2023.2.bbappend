# Override fancontrol configuration file, making this SC specific
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-xlnx:"

SRC_URI:append = " file://versal-sysmon-milli.patch"
SRC_URI:append = " file://0001-Return-max-temp-on-i2c-read-failure.patch"
