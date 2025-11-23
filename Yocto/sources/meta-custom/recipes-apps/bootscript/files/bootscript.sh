#!/bin/bash

echo -e "######## Bootscript Start! ########"

# Mount SD Card
mkdir -p /mnt/sd
mount /dev/mmcblk0p1 /mnt/sd
echo "Mounted SD"

# GPIO Loop
led=0
while true
do
    # Read GPIO
    echo "Buttons = $(peek 0xA0000000)"
    echo "DIP = $(peek 0xA0010000)"
    echo "LEDs = $(peek 0xA0020000)"

    # Increment LED and RGB Value
    if [ $led -eq 15 ]; then
        led=0
    else
        led=$((led+1))
    fi

    # Write GPIO
    poke 0xA0020000 $led
    echo "Wrote $led to 0x41210000"

    sleep 1
done

echo -e "######## Bootscript End! ########"