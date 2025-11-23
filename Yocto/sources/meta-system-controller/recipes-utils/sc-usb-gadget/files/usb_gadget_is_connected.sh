#!/bin/bash
udc=$(cat /sys/kernel/config/usb_gadget/g1/UDC)
if [ "$udc" == "" ]; then
	echo 0
else
	echo 1
fi
