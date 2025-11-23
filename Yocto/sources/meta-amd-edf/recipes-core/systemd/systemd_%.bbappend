# In order to support root partition and file system expansion, we want
# to always enable repart and openssl. These items will not cause any
# issues on systems where no systemd-repart conf file is supplied or
# where the systemd-repart and systemd-growfs-root services are not
# enabled.
PACKAGECONFIG:append = " repart openssl"

# Enable systemd-growfs-root.service by default.
# This does nothing unless a systemd-repart conf file is provided
# that triggers the growfs service.
SYSTEMD_SERVICE:${PN}:append = " systemd-growfs-root.service"
SYSTEMD_PACKAGES += "${PN}"

# Modify systemd-growfs-root.service file.
# Add WantedBy target so the service can be enabled by the user.
do_install:append() {
    echo "" >> ${D}${systemd_system_unitdir}/systemd-growfs-root.service
    echo "[Install]" >> ${D}${systemd_system_unitdir}/systemd-growfs-root.service
    echo "WantedBy=multi-user.target" >> ${D}${systemd_system_unitdir}/systemd-growfs-root.service
}
