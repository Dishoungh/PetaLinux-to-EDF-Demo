#! /bin/bash -e

### The following table controls the automatic generated of the machine .conf files (lines start with #M#)
### Machine                 Board                          PRE    POST
#M# qemu-microblaze-v-32    none                           none   none
#M# qemu-microblaze-v-64    none                           none   none

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
while read marker machine machine_id pre post ; do
  if [ "${marker}" != "#M#" ]; then
      continue
  fi

  # machines
  MACHINES[$count]=${machine}

  # URLs
  if [ ${machine_id} != "none" ]; then
    for mach in ${!MACHINE_ID[@]}; do
      if [ ${MACHINE_ID[${mach}]} = ${machine_id} ]; then
        URLS[$count]=${MACHINE_URL[${mach}]}
        break
      fi
    done
    if [ -z "${URLS[$count]}" ]; then
      echo "ERROR: Unable to find ${machine} in ${mach_index}" >&2
      exit 1
    fi
  else
    URLS[$count]=${machine_id}
  fi

  # pre
  if [ "$pre" = "none" ]; then
    pre=
  fi
  if [ "$machine_id" != "none" ]; then
    pre="${pre}\\n# This is a qemu test machine, use OE qemu config\\nrequire conf/machine/include/qemu.inc\\n"
  fi
  PRE[$count]=${pre}

  # post
  if [ "$post" = "none" ]; then
    post=
  fi
  POST[$count]=${post}

  count=$(expr $count + 1)
done < ${this}


for mach in ${!MACHINES[@]}; do
  if [ -n "$3" -a "$3" != "${MACHINES[${mach}]}" ]; then
    continue
  fi

  echo "Machine:      ${MACHINES[${mach}]}"
  echo "URL:          ${URLS[${mach}]}"
  echo "Pre:          ${PRE[${mach}]}"
  echo "Post:         ${POST[${mach}]}"
  echo

  set -x
  rm -rf output
  if [ ${URLS[${mach}]} != "none" ]; then
      gen-machineconf parse-sdt --hw-description ${URLS[${mach}]} -c ${conf_path} --machine-name ${MACHINES[${mach}]} ${add_args}
  fi
  set +x

  ######### Post gen-machineconf changes
  #
  if [ -n "${PRE[${mach}]}" ]; then
    sed -i ${conf_path}/machine/${MACHINES[${mach}]}.conf -e 's!\(# Required generic machine inclusion\)!'"${PRE[${mach}]}"'\n\1!'
  fi

  if [ -n "${POST[${mach}]}" ]; then
    sed -i ${conf_path}/machine/${MACHINES[${mach}]}.conf -e 's!\(^require conf/machine/.*\.conf\)!\1\n\n'"${POST[${mach}]}"'!'
  fi
done
