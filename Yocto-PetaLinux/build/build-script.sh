#!/bin/sh

bitbake petalinux-image-minimal

if [ $? -ne 0 ]; then
	echo "Failed to build image"
	exit 1
fi

# Copy image files into deployed-images
cp -L ./tmp/deploy/images/arty-z7/BOOT-arty-z7.bin ../sources/meta-custom/deployed-images/BOOT.BIN

if [ $? -ne 0 ]; then
	echo "Failed to copy BOOT.BIN to deploy directory"
	exit 1
fi

cp ./tmp/deploy/images/arty-z7/boot.scr ../sources/meta-custom/deployed-images/boot.scr

if [ $? -ne 0 ]; then
	echo "Failed to copy boot.scr to deploy directory"
	exit 1
fi

cp -L ./tmp/deploy/images/arty-z7/fitImage ../sources/meta-custom/deployed-images/image.ub

if [ $? -ne 0 ]; then
	echo "Failed to copy image.ub to deploy directory"
	exit 1
fi

cp -L ./tmp/deploy/images/arty-z7/system.dtb ../sources/meta-custom/deployed-images/system-dtb

if [ $? -ne 0 ]; then
	echo "Failed to copy device tree to deploy directory"
	exit 1
fi

echo "Image Build Successful!"
exit 0
