# eval-brd-sc-zynqmp needs to enable the CONFIG_VERSAL_SYSMON_I2C
KERNEL_FEATURES:append:eval-brd-sc-zynqmp = " features/versal-sysmon/versal-sysmon.scc"
KERNEL_FEATURES:append:eval-brd-sc-zynqmp = " features/hwmon/hwmon_modules.scc"
