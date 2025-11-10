# 0 "/opt/Xilinx/2025.1/PetaLinux/sysroots/x86_64-petalinux-linux/usr/lib/python3.12/site-packages/lopper/lops/lop-machine-name.dts"
# 0 "<built-in>"
# 0 "<command-line>"
# 1 "/opt/Xilinx/2025.1/PetaLinux/sysroots/x86_64-petalinux-linux/usr/lib/python3.12/site-packages/lopper/lops/lop-machine-name.dts"
# 10 "/opt/Xilinx/2025.1/PetaLinux/sysroots/x86_64-petalinux-linux/usr/lib/python3.12/site-packages/lopper/lops/lop-machine-name.dts"
/dts-v1/;

/ {
        compatible = "system-device-tree-v1,lop";
        lops {
                compatible = "system-device-tree-v1,lop";
                track_feature: track_feature {
                        compatible = "system-device-tree-v1,lop,code-v1";
                        noexec;
                        code = "
                            # print( 'track: lopper library routine: %s' % node )
                            try:
                                node.tunes[prop] = prop
                            except:
                                pass
                        ";
                };
                lop_0_1 {
                      compatible = "system-device-tree-v1,lop,select-v1";
                      select_1;
                      select_2 = "/";
                      lop_0_2 {
                              compatible = "system-device-tree-v1,lop,code-v1";
                              inherit = "subsystem";
                              code = "
                                    for n in tree.__selected__:
                                        mach_name = n['compatible'].value[0].replace(',','-').replace('.','-')
                                        model = n['model'].value[0]
                                        device_id = n['device_id'].value[0]
                                        print( '%s %s %s' % (mach_name.lower(), device_id, model) )
                        ";
                      };
                };
        };
};
