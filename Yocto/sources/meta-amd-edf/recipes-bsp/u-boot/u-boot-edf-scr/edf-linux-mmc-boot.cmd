# This is a boot script for U-Boot with Linux env for MMC boot mode.
# Generate edf_linux_mmc_boot.scr:
# mkimage -c none -A arm -T script -d edf-linux-mmc-boot.cmd edf_linux_mmc_boot.scr
#
################

setenv kernelname Image

setenv bootpartnum 2
setenv rootpartnum 3

echo "Checking for kernel:${kernelname}"
if test -e ${devtype} ${devnum}:${bootpartnum} ${kernelname}; then
       echo "Loading ${kernelname} at ${kernel_addr_r}"
       ext4load ${devtype} ${devnum}:${bootpartnum} ${kernel_addr_r} ${kernelname};
else
       echo "kernel image ${kernelname} not found on ${devtype} ${devnum}:${bootpartnum}"
       exit
fi

fdt addr ${fdtcontroladdr}
fdt get value bootargs /chosen bootargs
setenv bootargs ${bootargs} root=/dev/mmcblk${devnum}p${rootpartnum} ro rootwait uio_pdrv_genirq.of_id=generic-uio
bootefi ${kernel_addr_r} - ${fdtcontroladdr}
booti ${kernel_addr_r} - ${fdtcontroladdr}
