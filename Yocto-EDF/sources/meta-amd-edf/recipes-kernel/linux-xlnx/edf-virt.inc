FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://config_cgroup_bpf.cfg"

#FIXME - temporary work-around for this not being available yet
def kernel_cache_feature(src_uri,feature):
    return "../recipe-sysroot-native/kcfg/" + feature

KERNEL_FEATURES:append = " \
    ${@kernel_cache_feature(d.getVar('SRC_URI'),'cfg/net/bridge.scc')} \
    ${@kernel_cache_feature(d.getVar('SRC_URI'),'cfg/kubernetes.scc')} \
"
