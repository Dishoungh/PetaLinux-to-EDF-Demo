# If the user tries to build an image that is intended for a 'common' build
# warn them.

addhandler edf_check_image_event
edf_check_image_event[eventmask] = "bb.event.DepTreeGenerated"

AMD-EDF_WARN_IMAGES = "\
	edf-linux-disk-image \
	edf-image-everything \
	edf-image-full-cmdline \
"

AMD-EDF_IMAGE_SUPPORTED_MACHINES:pn-edf-xen-image-full-cmdline = "\
	amd-cortexa53-common \
	amd-cortexa53-mali-common \
	amd-cortexa72-common \
	amd-cortexa78-common \
	amd-cortexa78-mali-common \
"

AMD-EDF_IMAGE_SUPPORTED_MACHINES = "\
	amd-rv32imac-zicbom-zba-zbb-zbs-common \
	amd-rv64imafdc-zicbom-zba-zbb-zbs-common \
	amd-cortexa9thf-neon-common \
	amd-cortexa53-common \
	amd-cortexa53-mali-common \
	amd-cortexa72-common \
	amd-cortexa78-common \
	amd-cortexa78-mali-common \
"

python edf_check_image_event() {
    machine = d.getVar('MACHINE') or ""
    supported = d.getVar('AMD-EDF_IMAGE_SUPPORTED_MACHINES') or ""

    # Just return if this machine is one that is supported
    if machine in supported.split():
        return

    # Only a handful of targets/tasks need to be checked
    tasks_image = [t + '.do_rootfs' for t in d.getVar('AMD-EDF_WARN_IMAGES').split()]

    for mct in e._depgraph['tdepends']:
        t = mct.split(':')[-1]
        for x in tasks_image:
            if t == x:
                bb.warn("The image %s is not supported on %s, only machine(s) %s are supported.\n" % (x[:-10], machine, ", ".join(supported.split())))
}

python edf_check_rootfs() {
    machine = d.getVar('MACHINE')
    supported = d.getVar('AMD-EDF_IMAGE_SUPPORTED_MACHINES')
    if machine and supported and not machine in supported.split():
        bb.warn("This image is not supported on %s, only machine(s) %s are supported." % (machine, ", ".join(supported.split())))
}
