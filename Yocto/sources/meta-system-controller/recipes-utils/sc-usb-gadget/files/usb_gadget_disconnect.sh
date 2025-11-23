#!/bin/bash
# create a gadget
cd /sys/kernel/config/usb_gadget/g1 || exit 1
echo '' > UDC


if gpiofind USB_MUX_SEL > /dev/null; then
	libgpio=$(gpioget -v | head -n 1 | awk '{print $3}' | cut -d. -f1)
	if [ "$libgpio" ==  "v1" ]; then
		gpioset $(gpiofind USB_MUX_SEL)=0
	else
		gpioset -t0 USB_MUX_SEL=0
	fi
fi

