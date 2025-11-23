do_install:append:system-controller() {
    echo "************************************************************************" >> "${D}${sysconfdir}/motd"
    echo "*** NOTE: Configure System Settings 'sudo /usr/bin/system_config.sh' ***" >> "${D}${sysconfdir}/motd"
    echo "************************************************************************" >> "${D}${sysconfdir}/motd"

    # add the magic string to the "root" entry so systemd will resize/repart it
    sed -i '/\/dev\/root/d' ${D}${sysconfdir}/fstab
    echo "/dev/root / auto defaults,x-systemd.growfs 0 1" > ${D}${sysconfdir}/fstab.tmp
    cat ${D}${sysconfdir}/fstab >> ${D}${sysconfdir}/fstab.tmp
    mv ${D}${sysconfdir}/fstab.tmp ${D}${sysconfdir}/fstab
}
