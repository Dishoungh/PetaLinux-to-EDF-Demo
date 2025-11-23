FILESEXTRAPATHS:append := ":${THISDIR}/${PN}"

SRC_URI:append = "\
           file://0001-workaround-for-python-3.12.patch \
           "

SKIP_RECIPE[mongodb] = ""
