
HOMEPAGE = "https://github.com/OpenAMP/openamp-system-reference"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.md;md5=0b96a4c07d631aa5141bd3f058ba43b0"

REPO = "git://github.com/OpenAMP/openamp-system-reference;protocol=https"

# this sets the default SRCREV and BRANCH for all rpmsg examples
# if you wish to use the tip of main set the following in local.conf
# OPENAMP_SYS_REF_SRCREV="${AUTOREV}"
# OPENAMP_SYS_REF_BRANCH="main"
#
# These values select the commit tagged by main
OPENAMP_SYS_REF_SRCREV ?= "5d258336d40c019b1724ad9aa2d7fcc266a69d00"
OPENAMP_SYS_REF_BRANCH ?= "main"

# This include is used by multiple recipes
# now set the variables for this recipe
BRANCH  = "${OPENAMP_SYS_REF_BRANCH}"
SRCREV  = "${OPENAMP_SYS_REF_SRCREV}"

SRC_URI = "${REPO};branch=${BRANCH};"
