# Patch Xilinx QEMU

FILESEXTRAPATHS:prepend := "${THISDIR}/patches:"

SRC_URI += "file://0001-do-not-define-sched_attr-if-libc-headers-do.patch"