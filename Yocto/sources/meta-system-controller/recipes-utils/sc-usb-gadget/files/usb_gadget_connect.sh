#!/bin/bash

if gpiofind USB_MUX_SEL > /dev/null; then
	libgpio=$(gpioget -v | head -n 1 | awk '{print $3}' | cut -d. -f1)
	if [ "$libgpio" ==  "v1" ]; then
		gpioset $(gpiofind USB_MUX_SEL)=1
	else
		gpioset -t0 USB_MUX_SEL=1
	fi
fi

retry=0
while [ $(ls /sys/class/udc/ | wc -l) -eq 0 ]; do
	if [ $retry -eq 10 ]; then
		echo No usb device controllers
		exit 1
	fi
	sleep 1
	retry=$(( retry + 1 ))
done

FILE=$1

if [ "$FILE" == "" ]; then
	echo "Must specify usb disk image file"
	exit 1
fi

FILE=$(realpath "$FILE")
if [ ! -e "$FILE" ]; then
	echo "Unable to open $FILE"
	exit 1
fi

format=$(file "$FILE" | awk '{print $2}')
if [ "$format" == "gzip" ]; then
	gunzip "$FILE"
elif [ "$format" == "XZ" ]; then
	xz -d "$FILE"
fi

cd /sys/kernel/config/usb_gadget/g1 || exit 1

N="usb0"
echo "${FILE}" > functions/mass_storage.$N/lun.0/file

basename /sys/class/udc/* > UDC
