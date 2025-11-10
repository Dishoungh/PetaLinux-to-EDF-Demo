#Enables the restoration of the multi-boot register for A/B firmware platforms.
XLNX_CMAKE_BSP_VARS:append:versal-2ve-2vm-vek385-sdt-seg = " -DPLM_ENABLE_RESTORE_MULTIBOOT"
XLNX_CMAKE_BSP_VARS:append:versal-vek280-sdt-seg = " -DPLM_ENABLE_RESTORE_MULTIBOOT"
