#!/bin/bash

cd /sys/kernel/config/usb_gadget/g1 || exit 1
echo '' > UDC

find . -type l -exec unlink {} \;
rmdir configs/*/strings/*
rmdir configs/*
rmdir functions/*
rmdir strings/*
cd ..
rmdir g1
exit 0
