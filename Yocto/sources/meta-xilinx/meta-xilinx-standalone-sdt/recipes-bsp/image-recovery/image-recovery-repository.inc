IR_PATH ??= "${@d.getVarFlag('IR_PATH', d.getVar('MACHINE')) or ''}"
WEB_PATH ??= "${@d.getVarFlag('WEB_PATH', d.getVar('MACHINE')) or ''}"

# k26-smk
IR_PATH[k26-smk-sdt] = 'https://edf.amd.com/sswreleases/rel-v2025.2/image-recovery/2025.2/10090801/ImgRecovery.elf'
SRC_URI[k26-smk-sdt_ir.sha256sum] = 'f0eebf410b3397b70e2aa3f33d16cdb31e1699b64e75cbb0a2b7a00364f4ded5'

WEB_PATH[k26-smk-sdt] = 'https://edf.amd.com/sswreleases/rel-v2025.2/image-recovery/2025.2/10090801/web.img'
SRC_URI[k26-smk-sdt_web.sha256sum] = '0825518aea24c3104a58b0f847436db03a92d8d4366baad6594c4d03de09d8cb'

# k24-smk
IR_PATH[k24-smk-sdt] = 'https://edf.amd.com/sswreleases/rel-v2025.2/image-recovery/2025.2/10090801/ImgRecovery.elf'
SRC_URI[k24-smk-sdt_ir.sha256sum] = 'f0eebf410b3397b70e2aa3f33d16cdb31e1699b64e75cbb0a2b7a00364f4ded5'

WEB_PATH[k24-smk-sdt] = 'https://edf.amd.com/sswreleases/rel-v2025.2/image-recovery/2025.2/10090801/web.img'
SRC_URI[k24-smk-sdt_web.sha256sum] = '0825518aea24c3104a58b0f847436db03a92d8d4366baad6594c4d03de09d8cb'
