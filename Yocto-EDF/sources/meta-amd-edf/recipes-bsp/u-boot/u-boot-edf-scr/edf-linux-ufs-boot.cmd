# This is a boot script for U-Boot with Linux env for UFS boot mode.
# Generate edf_linux_ufs_boot.scr:
# mkimage -c none -A arm -T script -d edf-linux-ufs-boot.cmd edf_linux_ufs_boot.scr
#
################
setenv kernel_name Image
setenv bootpartnum 2
setenv rootpartnum 3

# UFS Boot
setenv devtype scsi
setenv devnum 0

echo "Checking for kernel:${kernel_name}"
if test -e ${devtype} ${devnum}:${bootpartnum} ${kernel_name}; then
	echo "Loading ${kernel_name} at ${kernel_addr_r}"
	ext4load ${devtype} ${devnum}:${bootpartnum} ${kernel_addr_r} ${kernel_name};
else
	echo "kernel image ${kernel_name} not found on ${devtype} ${devnum}:${bootpartnum}"
	exit
fi

part uuid ${devtype} ${devnum}:${rootpartnum} distro_rootpart_uuid;
fdt addr $fdtcontroladdr
fdt get value bootargs /chosen bootargs
setenv bootargs $bootargs root=PARTUUID=${distro_rootpart_uuid} rw rootwait;
bootefi ${kernel_addr_r} - $fdtcontroladdr
