BRANCH ?= "xlnx_rel_v2025.2"
REPO   ?= "git://github.com/Xilinx/multimedia-notebooks.git;protocol=https"
SRCREV ?= "204ed16c44478dfa1f426b22110c06fcf7da6259"

BRANCHARG = "${@['nobranch=1', 'branch=${BRANCH}'][d.getVar('BRANCH') != '']}"
SRC_URI = "${REPO};${BRANCHARG}"

PV .= "+git"
