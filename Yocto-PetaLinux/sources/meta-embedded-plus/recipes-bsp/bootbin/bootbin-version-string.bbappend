require xilinx-bootbin-version.inc

COMPATIBLE_MACHINE:emb-plus-ve2302-xrt = "${MACHINE}"

python do_configure:prepend:emb-plus-ve2302-xrt() {
    version = d.getVar("MACHINE") + "-v" + d.getVar("BOOTBIN_VER_MAIN")
}
