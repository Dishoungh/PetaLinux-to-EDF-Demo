VMR_PATH ??= "${@d.getVarFlag('VMR_PATH', d.getVar('MACHINE')) or ''}"

# emb-plus-ve2302-xrt
VMR_PATH[emb-plus-ve2302-xrt] = 'https://petalinux.xilinx.com/sswreleases/rel-v2024.2/vmr_images/2024.2/2024.2_1104_1_11050118/emb-plus-ve2302-sdt/vmr.elf'
SRC_URI[emb-plus-ve2302-xrt.sha256sum] = '51a43a621f5c3145cac0fca51d102b6e0af7d2f0f9fbabc50cb7fab6826279c0'

