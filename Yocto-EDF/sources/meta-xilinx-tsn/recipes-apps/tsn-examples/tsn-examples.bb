DESCRIPTION = "TSN talker listner examples"
LICENSE = "LGPL-2.1-only & BSD-3-Clause & GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = " \
	file://LICENSE.md;md5=0d4e0ec48c9308a5132d608a615aed9a \
	file://OpenAvnu/README.rst;beginline=41;endline=58;md5=421b581669f30d492238ab13a0b86cbd \
"

BRANCH = "main"
SRC_URI = " \
	gitsm://github.com/Xilinx/tsn-talker-listener.git;branch=${BRANCH};protocol=https \
	file://0001-openavb_tasks-Add-missing-include-file.patch \
"
SRCREV = "b68dc4efb12954623148d60e188d77f85f887acb"

S = "${WORKDIR}/git"

inherit autotools-brokensep

do_configure[noexec] = '1'
