FILESEXTRAPATHS:prepend:emb-plus-ve2302-xrt := "${THISDIR}/${PN}:"

SRC_URI:append:emb-plus-ve2302-xrt = " file://emb-plus-platform.cfg"
KERNEL_FEATURES:append:emb-plus-ve2302-xrt = " emb-plus-platform.cfg"

FILESEXTRAPATHS:prepend:emb-plus-ve2302-amr := "${THISDIR}/${PN}:"

SRC_URI:append:emb-plus-ve2302-amr = " file://emb-plus-platform.cfg"
KERNEL_FEATURES:append:emb-plus-ve2302-amr = " emb-plus-platform.cfg"
