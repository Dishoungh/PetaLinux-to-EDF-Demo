#!/bin/bash
# Generate the system-controller-app.inc file
#
# Usage:
# cd sources/meta-system-controller/scripts
# ./sc-app-repo-generate-srcuri.sh \
#    https://xcoartifactory.xilinx.com/artifactory/system-controller/sc_app_bsp/2024.2/2024.2_202509171418 <external|restricted|internal> \
#    > ../recipes/system-controller-app/system-controller-app.inc
#
# It is assumed the URL being pointed to will be a series of directories. The BOARD value
# will be the directory name, and the contents within the directory will be the board
# specific files.
#

external_path="${PWD}/../../meta-system-controller/recipes-utils/system-controller-app"
restricted_path="${PWD}/../../meta-system-controller-restricted/recipes-utils/system-controller-app"
internal_path="${PWD}/../../meta-xilinx-internal/meta-system-controller/recipes-utils/system-controller-app"

if [ ! -d ${external_path} ] || [ ! -d ${restricted_path} ] || [ ! -d ${internal_path} ]; then
	echo "missing a system controller repository"
	exit 1
fi

if [ $# -lt 1 -o $# -gt 3 ]; then
	echo "Usage: $0 <url> <external|restricted|internal> [<local_dir>]" >&2
	exit 1
fi

if [ $2 = "external" ]; then
	recipe_paths="${external_path}"
elif [ $2 = "restricted" ]; then
	recipe_paths="${restricted_path}"
elif [ $2 = "internal" ]; then
	recipe_paths="${external_path} ${restricted_path} ${internal_path}"
else
	echo "invalid release method"
	exit 1
fi

url=$(echo $1 | sed -e 's,/$,,')

localdir=${3}

urlproto=$(echo $url | sed -e 's,://.*,://,')
urlpath=$(echo $url | sed -e 's,'${urlproto}',,')

if [ ${url} = ${urlproto} -o -z ${url} ]; then
	echo "URL $url is invalid" >&2
	exit 1
fi

if [ ${urlproto} = "file://" ]; then
	# file:// URL, usually only for testing
	cd ${urlpath}
elif [ -n "${localdir}" ]; then
	# Remote url, but using a local cache to generate
	cd ${localdir}
else
	# Remove url, recursive fetch and then generate
	tempdir=$(mktemp -d)
	cd ${tempdir}
	wget --recursive --no-parent $url/

	cd ${urlpath}
fi

boards=""
for recipe_path in ${recipe_paths}; do
	for recipe in $(ls ${recipe_path}/systemcontroller-app-*.bb); do
		board=$(basename ${recipe} | sed 's/systemcontroller-app-//' | sed 's/\.bb//')
		boards="${boards} ${board}"
	done
done
boards=${boards^^}

echo "# Automatically generated.  Manual changes will be lost."
echo
echo "A01_ES1_PATH = \"\${@d.getVarFlag('A01_ES1_PATH', d.getVar('BOARD')) or ''}\""
echo "A02_ES1_PATH = \"\${@d.getVarFlag('A02_ES1_PATH', d.getVar('BOARD')) or ''}\""
echo "ES1_PATH = \"\${@d.getVarFlag('ES1_PATH', d.getVar('BOARD')) or ''}\""
echo "SYS_PATH = \"\${@d.getVarFlag('SYS_PATH', d.getVar('BOARD')) or ''}\""
echo "ELF_PATH = \"\${@d.getVarFlag('ELF_PATH', d.getVar('BOARD')) or ''}\""
echo "JSON_PATH = \"\${@d.getVarFlag('JSON_PATH', d.getVar('BOARD')) or ''}\""
echo "JPG_PATH = \"\${@d.getVarFlag('JPG_PATH', d.getVar('BOARD')) or ''}\""
echo "PNG_PATH = \"\${@d.getVarFlag('PNG_PATH', d.getVar('BOARD')) or ''}\""
echo "JS_PATH = \"\${@d.getVarFlag('JS_PATH', d.getVar('BOARD')) or ''}\""
echo "BOOT_BIN_PATH = \"\${@d.getVarFlag('BOOT_BIN_PATH', d.getVar('BOARD')) or ''}\""
echo "OSPI_PATH = \"\${@d.getVarFlag('OSPI_PATH', d.getVar('BOARD')) or ''}\""
echo

releases="external internal"
for release in ${releases}; do
	dirnames=$(curl --silent ${urlproto}${urlpath}/${release}/ | grep -o 'href=".*">' | sed 's/href="//;s/\/">//')
	for dir in $dirnames; do 
		if [ "$dir" == ".." ]; then
			continue
		fi

		echo "$boards " | grep -q "$dir " || continue
		files=$(curl --silent "${urlproto}${urlpath}/${release}/$dir/" | grep -o 'href=".*">' | sed -e "s/href=\"//g" | sed -e 's/">//g')
		echo "# $dir"
		device=$(basename "$dir")
		device="${device,,}"
		for i in $files; do
			opts=''
			if [ "$i" == "../" ]; then
				continue
			fi
			if [[ "$i" == *"elf"* ]]; then
				name=$device-elf
				yp_name="ELF_PATH"
			elif [[ "$i" == *"pdi"* ]] && [[ "$i" == *"A01_es1"* ]]; then
				name=$device-A01-es1-system
				yp_name="A01_ES1_PATH"
			elif [[ "$i" == *"pdi"* ]] && [[ "$i" == *"A02_es1"* ]]; then
				name=$device-A02-es1-system
				yp_name="A02_ES1_PATH"
			elif [[ "$i" == *"pdi"* ]] && [[ "$i" == *"es1"* ]]; then
				name=$device-es1-system
				yp_name="ES1_PATH"
			elif [[ "$i" == *"pdi"* ]]; then
				name=$device-system
				yp_name="SYS_PATH"
			elif [[ "$i" == *"xsa"* ]]; then
				continue
			elif [[ "$i" == *"json"* ]]; then
				name=$device-json
				yp_name="JSON_PATH"
			elif [[ "$i" == *"strings"* ]]; then
				name=$device-js
				yp_name="JS_PATH"
			elif [[ "$i" == *"jpg"* ]]; then
				name=$device-jpg
				yp_name="JPG_PATH"
			elif [[ "$i" == *"png"* ]]; then
				name=$device-png
				yp_name="PNG_PATH"
			elif [[ "$i" == *"BOOT"* ]]; then
				name=$device-boot-bin
				yp_name="BOOT_BIN_PATH"
			elif [[ "$i" == *"bin.gz"* ]]; then
				name=$device-ospi
				yp_name="OSPI_PATH"
				opts=';unpack=0'
			fi
			echo "# $i"
			echo ${yp_name}["${device}"] = "'${urlproto}${urlpath}/${release}/${dir}/${i};name=${name}${opts}'"
			curl -o tempfile --silent "${urlproto}${urlpath}/${release}/$dir/$i"
			echo SRC_URI["${name}".sha256sum] = "'$(sha256sum tempfile | awk '{print $1}')'"
			echo
		done
	done
done
rm tempfile
