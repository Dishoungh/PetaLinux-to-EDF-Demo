FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Audit is required or systemd can fail to start the journal
SRC_URI += "file://audit.cfg"
