# This is a boot script for U-Boot (only supported modes are SD and QSPI).
# Generate boot.scr:
# mkimage -c none -A arm -T script -d custom.boot.cmd boot.scr

fit_name=image.ub
load_addr=0x10000000
fit_offset=0x00A80000
fit_size=0x0C800000

for boot_target in ${boot_targets};
do
    echo "Trying to load boot images from ${boot_target}"
    if test "${boot_target}" = "mmc0" || test "${boot_target}" = "mmc1"; then
        if test -e ${devtype} ${devnum}:${distro_bootpart} /${fit_name}; then
            fatload ${devtype} ${devnum}:${distro_bootpart} ${load_addr} ${fit_name};
            bootm ${load_addr};
            echo "SD booting using FIT image failed"
        else
            echo "Failed to find FIT image for SD boot"
        fi
    fi
    
    if test "${boot_target}" = "xspi0" || test "${boot_target}" = "xspi1" || test "${boot_target}" = "qspi" || test "${boot_target}" = "qspi0"; then
        sf probe 0 0 0;
        sf read ${load_addr} ${fit_offset} ${fit_size}
        bootm ${load_addr};
        echo "Booting using Fit image failed"
    fi
done