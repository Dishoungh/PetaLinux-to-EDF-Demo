SUMMARY = "ISP Media Server and Libraries"
DESCRIPTION = "ISP Media Server application and required shared libraries."
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=c7774c4d8c75e31e42f030d5369a1112"
COMPATIBLE_HOST = ".*-linux"

S = "${WORKDIR}"

SRC_URI = "https://edf.amd.com/sswreleases/isp-media-server/2025.2/10242025/isp-media-server_24102025.tar.gz"
SRC_URI[sha256sum] = "c2a98bdb5d64f4cbb3d8a78f384902b7882c984a6808a36b68b66a1c43814d9c"

RDEPENDS:${PN} += "kernel-module-isp"

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${bindir}

    install -d ${D}${datadir}

    cp -r ${WORKDIR}/Tuning_files ${D}${datadir}

    # Make sure necessary symlinks are created
    for fullso in *.so.*.* *.so.* *.so ; do
        soname=$(objdump -p $fullso | grep SONAME | xargs echo | cut -d ' ' -f 2)
        if [ -z "$soname" ]; then
            # Fallback if blank/undefined
            soname=${fullso}
        fi
        if [ -e ${D}${libdir}/${soname} ]; then
            # Already present
            continue
        fi
        if [ ! -e ${D}${libdir}/$fullso ]; then
            # Copy and set soname symlink (if different)
            install -m 0755 $fullso ${D}${libdir}/.
            if [ ! -e ${D}${libdir}/$soname ]; then
                ln -s $fullso ${D}${libdir}/$soname
            fi
        fi
    done

    # Install binary
    install -m 0755 *isp_media_server ${D}${bindir}/
}

FILES:${PN} += "${libdir}/*.so* ${bindir}/*isp_media_server ${datadir}/Tuning_files/*"

# Disable all QA checks and stripping
INSANE_SKIP:${PN} += "already-stripped ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
do_populate_sysroot[noexec] = "1"

# Optional: mark package as arch-independent if safe
PACKAGE_ARCH = "${MACHINE_ARCH}"


FILES:${PN}-dev = ""


