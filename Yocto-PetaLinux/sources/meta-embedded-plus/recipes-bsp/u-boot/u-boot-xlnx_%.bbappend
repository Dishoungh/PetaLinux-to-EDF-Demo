FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:emb-plus-ve2302-xrt = " file://emb-plus.cfg"
SRC_URI:append:emb-plus-ve2302-amr = " file://emb-plus-amr.cfg"
