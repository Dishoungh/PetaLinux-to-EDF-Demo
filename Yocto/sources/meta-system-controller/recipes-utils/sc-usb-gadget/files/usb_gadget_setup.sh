#!/bin/bash
# create a gadget
cd /sys/kernel/config/usb_gadget || exit 1
mkdir -p g1
cd g1 || exit 1
#echo '' > UDC

# configure it (vid/pid can be anything if USB Class is used for driver compat)
echo 0x1d6b > idVendor
echo 0x0104 > idProduct
# configure its serial/mfg/product
mkdir strings/0x409
#echo myserial > strings/0x409/serialnumber
echo "AMD" > strings/0x409/manufacturer
echo "System Controller" > strings/0x409/product

N="usb0"

# create configs
mkdir configs/c.1

# Mass Storage Device
mkdir functions/mass_storage.$N
ln -s functions/mass_storage.$N configs/c.1

# Network ECM Device
mkdir -p functions/ecm.$N

HOST="02:0a:35:00:00:02"
SELF="02:0a:35:00:00:01"
echo $HOST > functions/ecm.$N/host_addr
echo $SELF > functions/ecm.$N/dev_addr

ln -s functions/ecm.$N configs/c.1/

# Serial ACM Device
mkdir -p functions/acm.$N
ln -s functions/acm.$N configs/c.1/


# Create a hostname alias for the Versal Device
if ! grep versal /etc/hosts > /dev/null; then
	echo "172.31.254.2 versal" >> /etc/hosts
fi

