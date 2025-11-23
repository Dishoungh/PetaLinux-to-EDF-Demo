# /cpus_microblaze@0/cpu@0
#   compatible = "pmc-microblaze";
AVAILTUNES += "microblaze-pmc"
TUNE_FEATURES:tune-microblaze-pmc = "microblaze v10.0 barrel-shift pattern-compare reorder multiply-high divide-hard fpu-soft"
PACKAGE_EXTRA_ARCHS:tune-microblaze-pmc = "${TUNE_PKGARCH}"


require conf/machine/include/xilinx-microblaze.inc
