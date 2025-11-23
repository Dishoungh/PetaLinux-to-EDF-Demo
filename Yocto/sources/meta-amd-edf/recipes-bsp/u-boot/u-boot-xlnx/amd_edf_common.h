#ifndef __CONFIG_EDF_COMMON_H
#define __CONFIG_EDF_COMMON_H

#define ENV_EDF_SETTINGS \
    "firstboot_saveenv=if test ! \"\${firstboot}\" = \"false\"; then setenv firstboot false; saveenv; saveenv; fi \0"

#endif /* __CONFIG_EDF_COMMON_H */
