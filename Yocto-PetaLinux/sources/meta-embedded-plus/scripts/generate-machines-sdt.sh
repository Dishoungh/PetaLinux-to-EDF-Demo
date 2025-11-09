#! /bin/bash -e

### The following table controls the automatic generated of the machine .conf files (lines start with #M#)
### Machine               MULTICONFIGS                                           OVERLAY  DOMAIN   OVERRIDES       PRE   POST
#M# emb-plus-ve2302-xrt   default       					 none     default  none            none  none
#M# emb-plus-ve2302-amr   --add-config\ CONFIG_YOCTO_BBMC_CORTEXR5_0_FREERTOS=y  full     default  emb-plus-amr    none  none

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

  MACHINE_ID[$count]=${mach_id}
  MACHINE_URL[$count]=${mach_url}

  count=$(expr $count + 1)
done < ${mach_index}


# Load in the arrays from this script
count=0
while read marker machine multiconfigs overlay domain overrides pre post ; do
  if [ "${marker}" != "#M#" ]; then
      continue
  fi

  # machines
  MACHINES[$count]=${machine}

  # multiconfigs
  if [ "$multiconfigs" = "full" ]; then
    multiconfigs=" --multiconfigfull "
  elif [ "$multiconfigs" = "default" ]; then
    multiconfigs=""
  fi
  MULTICONFIGS[$count]=${multiconfigs}

  # overlays
  if [ "$overlay" = "full" ]; then
    overlay=" -g full "
  else
    overlay=""
  fi
  OVERLAYS[$count]=${overlay}

  # domains
  if [ "$domain" = "default" ]; then
    domain=""
  else
    dir=$(dirname $this)
    domain=" --domain-file ${dir}/${domain} "
  fi
  DOMAINS[$count]=${domain}

  # machine_overrides
  if [ "$overrides" = "none" ]; then
    overrides=""
  else
    overrides="-O ${overrides} "
  fi
  OVERRIDES[$count]=${overrides}

  # URLs
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

  # pre
  if [ "$pre" = "none" ]; then
    pre=""
  fi
  PRE[$count]=${pre}

  # post
  if [ "$post" = "none" ]; then
    post=""
  fi
  POST[$count]=${post}

  count=$(expr $count + 1)
done < ${this}


for mach in ${!MACHINES[@]}; do
  if [ -n "$3" -a "$3" != "${MACHINES[${mach}]}" ]; then
    continue
  fi

  echo "Machine:      ${MACHINES[${mach}]}"
  echo "Multiconfigs: ${MULTICONFIGS[${mach}]}"
  echo "Overlay:      ${OVERLAYS[${mach}]}"
  echo "Domain:       ${DOMAINS[${mach}]}"
  echo "Overrides:    ${OVERRIDES[${mach}]}"
  echo "URL:          ${URLS[${mach}]}"
  echo "Pre:          ${PRE[${mach}]}"
  echo "Post:         ${POST[${mach}]}"
  echo

  if [ ${MACHINES[${mach}]} = "emb-plus-ve2302-amr" ]; then
    add_args="--add-config CONFIG_SUBSYSTEM_TF-A_SERIAL_SERIAL1_SELECT=y \
     --add-config CONFIG_SUBSYSTEM_SERIAL_TF-A_IP_NAME="pl011_1" \
     "
  else
    add_args=""
  fi

  set -x
  rm -rf output
  gen-machineconf parse-sdt --hw-description ${URLS[${mach}]} -c ${conf_path} --machine-name ${MACHINES[${mach}]} ${MULTICONFIGS[${mach}]} ${OVERLAYS[${mach}]} ${DOMAINS[${mach}]} ${OVERRIDES[${mach}]} ${add_args}
  rm -f ${conf_path}/domains.yaml
  rm -f ${conf_path}/system-top.dts.pp
  set +x

  ######### Post gen-machineconf changes
  #
  if [ -n "${PRE[${mach}]}" ]; then
    sed -i ${conf_path}/machine/${MACHINES[${mach}]}.conf -e 's!\(# Required generic machine inclusion\)!'"${PRE[${mach}]}"'\n\1!'
  fi

  if [ -n "${POST[${mach}]}" ]; then
    sed -i ${conf_path}/machine/${MACHINES[${mach}]}.conf -e 's!\(^require conf/machine/.*\.conf\)!\1\n\n'"${POST[${mach}]}"'!'
  fi

   # Manipulate configuration variables
  case ${MACHINES[${mach}]} in
    emb-plus-ve2302-amr)
      sed -i ${conf_path}/machine/${MACHINES[${mach}]}.conf \
        -e 's,UBOOT_ENTRYPOINT  ?= "0x200000",UBOOT_ENTRYPOINT  ?= "0x8200000",' \
        -e 's,UBOOT_LOADADDRESS ?= "0x200000",UBOOT_LOADADDRESS ?= "0x8200000",' \
      ;;
  esac
done
