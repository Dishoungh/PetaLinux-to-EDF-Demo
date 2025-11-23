# Include Hello World MC app for specific machine only.
include ${@'versal-vrk160-sdt-seg-hello-world.inc' if ((d.getVar('MACHINE') == 'versal-vrk160-sdt-seg') and (d.getVar('BB_CURRENT_MC') != 'xilinx-image-recovery')) else ''}
include ${@'versal-2ve-2vm-hello-world-r52-1-baremetal.inc' if ((d.getVar('MACHINE') in ['versal-2ve-2vm-vek385-sdt-seg', 'versal-2ve-2vm-vek385-revb-sdt-seg']) and (d.getVar('BB_CURRENT_MC') != 'xilinx-image-recovery')) else ''}
