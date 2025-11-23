#! /bin/bash -e

XSCT_VERSION=2025.2

### The following table controls the automatic generated of the machine .conf files (lines start with #M#)
### Machine               Board Template          PRE     POST
#M# eval-brd-sc-zynqmp    zynqmp-sc-revb          #\ Yocto\ uboot\ device-tree\ variables\\nYAML_CONSOLE_DEVICE_CONFIG:pn-uboot-device-tree\ ?=\ \"psu_uart_1\"\\n    # Add the following files to /boot\\nIMAGE_BOOT_FILES += \"system-sc-revc-zynqmp.dtb system.dtb\"\\nWKS_FILES = \"system-controller-nobootpartition.wks\"\\n\\n# enable RAUC_DEMO for this board by default\\nSYSTEM_CONTROLLER_RAUC_DEMO = \"yes\"
#M# vck-sc-zynqmp         zynqmp-e-a2197-00-revb  none    # Add the following files to pack in wic FAT partition\\nIMAGE_BOOT_FILES += \"boot.bin system.dtb\"\\n# Pack bitstream in BOOT.BIN for vck-sc-zynqmp\\nBIF_BITSTREAM_ATTR  = \"bitstream\"
#M# zynqmp-k24-sc-xsct-base  zynqmp-sc-k24-reva   none    # Add the following files to /boot\\nIMAGE_BOOT_FILES += "system.dtb"\\nWKS_FILES = "system-controller-nobootpartition.wks"

this=$(realpath $0)

if [ $# -lt 2 ]; then
  echo "$0: <conf_path> <machine_url_index> [machine]" >&2
  exit 1
fi

gmc=`which gen-machineconf`
if [ -z "${gmc}" ]; then
  echo "ERROR: This script must be run in a configured Yocto Project build with gen-machineconf in the environment." >&2
  exit 1
fi

conf_path=$(realpath $1)
if [ ! -d ${conf_path} ]; then
  mkdir -p ${conf_path}
fi


mach_index=$(realpath $2)
count=0
while read mach_id mach_url; do
  if [ ${mach_id} = '#' ]; then
      continue
  fi

  case ${mach_id} in
    eval-brd-sc)         mach_id="eval-brd-sc-zynqmp" ;;
  esac

  MACHINE_ID[$count]=${mach_id}
  MACHINE_URL[$count]=${mach_url}

  count=$(expr $count + 1)
done < ${mach_index}


# Load in the arrays from this script
count=0
while read marker machine board pre post ; do
  if [ "${marker}" != "#M#" ]; then
      continue
  fi

  MACHINES[$count]=${machine}
  BOARDS[$count]=${board}
  for mach in ${!MACHINE_ID[@]}; do
    if [ ${MACHINE_ID[${mach}]} = ${machine} ]; then
      URLS[$count]=${MACHINE_URL[${mach}]}
      break
    fi
  done
  if [ -z "${URLS[$count]}" ]; then
    echo "ERROR: Unable to find ${machine} in ${mach_index}" >&2
    exit 1
  fi
  if [ "$pre" = "none" ]; then
    pre=
  fi
  PRE[$count]=${pre}
  POST[$count]=${post}

  count=$(expr $count + 1)
done < ${this}


for mach in ${!MACHINES[@]}; do
  if [ -n "$3" -a "$3" != "${MACHINES[${mach}]}" ]; then
    continue
  fi

  echo "Machine: ${MACHINES[${mach}]}"
  echo "Board:   ${BOARDS[${mach}]}"
  echo "URL:     ${URLS[${mach}]}"
  echo
  set -x
  rm -rf output
  if [ ${MACHINES[${mach}]} = "vck-sc-zynqmp" ]; then
    add_args=""
  else
    add_args="--add-config CONFIG_SUBSYSTEM_FPGA_MANAGER=y"
  fi
  gen-machineconf parse-xsa --hw-description ${URLS[${mach}]} -c ${conf_path} --machine-name ${MACHINES[${mach}]} --require-machine system-controller-generic --add-config CONFIG_SUBSYSTEM_MACHINE_NAME=\"${BOARDS[${mach}]}\" ${add_args}
  set +x

  ######### Post gen-machineconf changes
  #
  # Since this is a version specific XSA, set the version of XSCT to use.
  if [ -n "${PRE[${mach}]}" ]; then
    sed -i ${conf_path}/machine/${MACHINES[${mach}]}.conf -e 's,\(# Required generic machine inclusion\),'"${PRE[${mach}]}"'\n\1,'
  fi

  if [ -n "${POST[${mach}]}" ]; then
    sed -i ${conf_path}/machine/${MACHINES[${mach}]}.conf -e 's,\(^require conf/machine/.*\.conf\),\1\n\n'"${POST[${mach}]}"','
  fi

  case ${MACHINES[${mach}]} in
    eval-brd-sc-zynqmp)
      sed -i ${conf_path}/machine/${MACHINES[${mach}]}.conf \
        -e 's:Machine configuration for the eval-brd-sc-zynqmp boards.:Machine configuration for vkp120, vckp180, vhk158, vek280 system controller.:' \
        -e 's,YAML_CONSOLE_DEVICE_CONFIG:pn-device-tree ?= "psu_uart_0",YAML_CONSOLE_DEVICE_CONFIG:pn-device-tree ?= "psu_uart_1",' \
        -e 's,ATF_CONSOLE ?= "cadence",ATF_CONSOLE ?= "cadence1",' \
        -e 's,YAML_SERIAL_CONSOLE_STDIN:pn-pmu-firmware ?= "psu_uart_0",YAML_SERIAL_CONSOLE_STDIN:pn-pmu-firmware ?= "psu_uart_1",' \
        -e 's,YAML_SERIAL_CONSOLE_STDOUT:pn-pmu-firmware ?= "psu_uart_0",YAML_SERIAL_CONSOLE_STDOUT:pn-pmu-firmware ?= "psu_uart_1",' \
        -e 's,YAML_SERIAL_CONSOLE_STDIN:pn-fsbl-firmware ?= "psu_uart_0",YAML_SERIAL_CONSOLE_STDIN:pn-fsbl-firmware ?= "psu_uart_1",' \
        -e 's,YAML_SERIAL_CONSOLE_STDOUT:pn-fsbl-firmware ?= "psu_uart_0",YAML_SERIAL_CONSOLE_STDOUT:pn-fsbl-firmware ?= "psu_uart_1",' \
      ;;
    vck-sc-zynqmp)
      sed -i ${conf_path}/machine/${MACHINES[${mach}]}.conf \
        -e 's:Machine configuration for the vck-sc-zynqmp boards.:Machine configuration for vck190 and vmk180 system controller.:'
      ;;
    zynqmp-k24-sc-xsct-base)
      sed -i ${conf_path}/machine/${MACHINES[${mach}]}.conf \
        -e 's:Machine configuration for the zynqmp-k24-sc-xsct-base boards.:Machine configuration for kria k24 based system controller.:' \
        -e 's,YAML_CONSOLE_DEVICE_CONFIG:pn-device-tree ?= "psu_uart_0",YAML_CONSOLE_DEVICE_CONFIG:pn-device-tree ?= "psu_uart_1",' \
        -e 's,TFA_CONSOLE ?= "cadence",TFA_CONSOLE ?= "cadence1",' \
        -e 's,YAML_SERIAL_CONSOLE_STDIN:pn-pmu-firmware ?= "psu_uart_0",YAML_SERIAL_CONSOLE_STDIN:pn-pmu-firmware ?= "psu_uart_1",' \
        -e 's,YAML_SERIAL_CONSOLE_STDOUT:pn-pmu-firmware ?= "psu_uart_0",YAML_SERIAL_CONSOLE_STDOUT:pn-pmu-firmware ?= "psu_uart_1",' \
        -e 's,YAML_SERIAL_CONSOLE_STDIN:pn-fsbl-firmware ?= "psu_uart_0",YAML_SERIAL_CONSOLE_STDIN:pn-fsbl-firmware ?= "psu_uart_1",' \
        -e 's,YAML_SERIAL_CONSOLE_STDOUT:pn-fsbl-firmware ?= "psu_uart_0",YAML_SERIAL_CONSOLE_STDOUT:pn-fsbl-firmware ?= "psu_uart_1",' \
        -e '4i MACHINEOVERRIDES =. \"eval-brd-sc-zynqmp:\"'
      ;;
  esac
done
