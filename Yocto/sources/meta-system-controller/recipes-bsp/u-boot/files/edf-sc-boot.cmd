# This is a boot script for U-Boot with Linux env for MMC boot mode.
# Generate boot.scr:
# mkimage -c none -A arm -T script -d edf-sc-boot.cmd boot.scr
#
################

setenv kernelname /boot/Image

setenv rootpartnum 1

echo "Checking for kernel:${kernelname}"
if test -e ${devtype} ${devnum}:${rootpartnum} ${kernelname}; then
       echo "Loading ${kernelname} at ${kernel_addr_r}"
       ext4load ${devtype} ${devnum}:${rootpartnum} ${kernel_addr_r} ${kernelname};
else
       echo "kernel image ${kernelname} not found on ${devtype} ${devnum}:${rootpartnum}"
       exit
fi

fdt addr ${fdtcontroladdr}
fdt get value bootargs /chosen bootargs
setenv bootargs ${bootargs} root=/dev/mmcblk${devnum}p${rootpartnum} ro rootwait uio_pdrv_genirq.of_id=generic-uio
bootefi ${kernel_addr_r} - ${fdtcontroladdr}
booti ${kernel_addr_r} - ${fdtcontroladdr}
