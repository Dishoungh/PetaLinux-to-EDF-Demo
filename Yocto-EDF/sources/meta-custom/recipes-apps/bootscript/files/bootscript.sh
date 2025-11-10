#!/bin/bash

echo -e "######## Bootscript Start! ########"

# Mount SD Card
mkdir -p /mnt/sd
mount /dev/mmcblk0p1 /mnt/sd
echo "Mounted SD"

# GPIO Loop
led=0
rgb=0
while true
do
    # Read GPIO
    echo "Buttons = $(peek 0x41200000)"
    echo "LEDs = $(peek 0x41210000)"
    echo "RGB = $(peek 0x41220000)"
    echo "Switches = $(peek 0x41230000)"

    # Increment LED and RGB Value
    if [ $led -eq 15 ]; then
        led=0
    else
        led=$((led+1))
    fi

    if [ $rgb -eq 63 ]; then
        rgb=0
    else
        rgb=$((rgb+1))
    fi

    # Write GPIO
    poke 0x41210000 $led
    echo "Wrote $led to 0x41210000"

    poke 0x41220000 $rgb
    echo "Wrote $rgb to 0x41220000"

    sleep 1
done

echo -e "######## Bootscript End! ########"