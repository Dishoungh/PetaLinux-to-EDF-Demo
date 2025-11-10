# This is a boot script for U-Boot with Xen parameters for SD boot mode.
# Generate edf_xen_boot.scr:
# mkimage -c none -A arm -T script -d edf-xen-boot.cmd edf_xen_boot.scr
#
################
xen_name=@@XEN_IMAGETYPE@@
kernel_name=@@KERNEL_IMAGETYPE@@
rootfs_name=@@RAMDISK_IMAGETYPE@@

setenv devtype mmc
setenv devnum @@SDBOOTDEV@@
setenv bootdev mmcblk${devnum}p
setenv bootpart @@BOOTPARTNUM@@

echo "Checking for xen:${xen_name}"
if test -e ${devtype} ${devnum}:${bootpart} ${xen_name}; then
	echo "Loading ${xen_name} at @@XEN_LOAD_ADDRESS@@";
	ext4load ${devtype} ${devnum}:${bootpart} @@XEN_LOAD_ADDRESS@@ ${xen_name};
fi

echo "Checking for Image:${kernel_name}"
if test -e ${devtype} ${devnum}:${bootpart} ${kernel_name}; then
	echo "Loading ${kernel_name} at @@XEN_KERNEL_LOAD_ADDRESS@@";
	ext4load ${devtype} ${devnum}:${bootpart} @@XEN_KERNEL_LOAD_ADDRESS@@ ${kernel_name};
	setenv kernel_size 0x$filesize;
fi

echo "Checking for ramdisk:${rootfs_name}"
if test -e ${devtype} ${devnum}:${bootpart} ${rootfs_name} && test "${skip_ramdisk}" != "yes"; then
	echo "Loading ${rootfs_name} at @@XEN_RAMDISK_LOAD_ADDRESS@@";
	ext4load ${devtype} ${devnum}:${bootpart} @@XEN_RAMDISK_LOAD_ADDRESS@@ ${rootfs_name};
	setenv ramdisk_size 0x$filesize;
fi

fdt addr $fdtcontroladdr
fdt resize 0x1000
fdt get value bootargs /chosen bootargs
fdt set /chosen \#address-cells <0x2>
fdt set /chosen \#size-cells <0x2>
setenv xen_bootargs "console=dtuart dtuart=@@XEN_SERIAL_CONSOLES@@ dom0_mem=@@DOM0_MEM@@ dom0_max_vcpus=@@DOM0_MAX_VCPUS@@ bootscrub=0 vwfi=native @@XEN_CMDLINE_APPEND@@"
fdt set /chosen xen,xen-bootargs \"${xen_bootargs}\"

# Check that env $ramdisk_size is 0 or not.
if test -n ${ramdisk_size}; then
	fdt mknod /chosen dom0-ramdisk
	fdt set /chosen/dom0-ramdisk compatible  "xen,linux-initrd" "xen,multiboot-module" "multiboot,module"
	fdt set /chosen/dom0-ramdisk reg <0x0 @@XEN_RAMDISK_LOAD_ADDRESS@@ 0x0 ${ramdisk_size}>
	setenv rootfs_param @@KERNEL_ROOT_RAMDISK@@
else
    setenv rootfs_param @@KERNEL_ROOT_SD@@
fi

fdt mknod /chosen dom0
fdt set /chosen/dom0 compatible  "xen,linux-zimage" "xen,multiboot-module" "multiboot,module"
fdt set /chosen/dom0 reg <0x0 @@XEN_KERNEL_LOAD_ADDRESS@@ 0x0 ${kernel_size}>
setenv dom0_bootargs "console=hvc0 earlycon=xen earlyprintk=xen clk_ignore_unused ${rootfs_param}"
fdt set /chosen xen,dom0-bootargs \"${dom0_bootargs}\"

setenv fdt_high 0xffffffffffffffff

booti @@XEN_LOAD_ADDRESS@@ - $fdtcontroladdr
