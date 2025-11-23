FILESEXTRAPATHS:prepend := "${THISDIR}/shared-queues-vendor:"
SRC_URI:remove = "file://0001-CMakeLists.txt-fetch-readerwriterqueue-and-concurren.patch"
SRC_URI:append = "file://0001-CMakeLists.txt-fetch-readerwriterqueue.patch"
SRCREV_release:forcevariable = "fea3a738733673c927d77631a735157993ff6395"
