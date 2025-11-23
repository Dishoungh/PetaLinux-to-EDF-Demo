inherit esw

ESW_COMPONENT_SRC = "/lib/sw_services/xilplmi/src/"
ESW_COMPONENT_NAME = "libxilplmi.a"

DEPENDS += " \
    xilstandalone \
    libxil \
    cfupmc \
    xiltimer \
    ${@'xilmailbox' if d.getVar('ESW_MACHINE') in ['psv_cortexa72_0', 'psv_cortexr5_0', 'pmc_0', 'psx_pmc_0', 'psx_psm_0'] else ''} "
