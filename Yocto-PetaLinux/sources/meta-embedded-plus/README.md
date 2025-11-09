# meta-embedded-plus

This layer enables AMD Embedded Plus platform.

## Embedded Plus buildable machines and images

The following MACHINE and build target combinations are possible to build artifacts in SDT flow for
the Embedded Plus platform:

| Machine             | Target             | Description            |
|---------------------|--------------------|------------------------|
| emb-plus-ve2302-amr | emb-plus-apu-image | APU image              |
| emb-plus-ve2302-amr | emb-plus-ospi-amr  | OSPI image             |
| emb-plus-ve2302-xrt | emb-plus-apu-image | APU image              |
| emb-plus-ve2302-xrt | emb-plus-ospi-vmr  | OSPI image             |

> Usageexample:
>
> MACHINE=emb-plus-ve2302-amr bitbake emb-plus-apu-image

## Maintainers, Patches/Submissions, Community

Please send any patches, pull requests, comments or questions for this layer to
the [meta-xilinx mailing list](https://lists.yoctoproject.org/g/meta-xilinx):

	meta-xilinx@lists.yoctoproject.org

When sending patches, please make sure the email subject line includes
`[meta-embedded-plus][<BRANCH_NAME>][PATCH]` and cc'ing the maintainers.

For more details follow the OE community patch submission guidelines, as described in:

https://www.openembedded.org/wiki/Commit_Patch_Message_Guidelines
https://www.openembedded.org/wiki/How_to_submit_a_patch_to_OpenEmbedded

`git send-email --to meta-xilinx@lists.yoctoproject.org *.patch`

> **Note:** When creating patches, please use below format. To follow best practice,
> if you have more than one patch use `--cover-letter` option while generating the
> patches. Edit the `0000-cover-letter.patch` and change the title and top of the
> body as appropriate.

**Syntax:**
`git format-patch -s --subject "meta-embedded-plus][<BRANCH_NAME>][PATCH" -1`

**Example:**
`git format-patch -s --subject "meta-embedded-plus][rel-v2025.1][PATCH" -1`

**Maintainers:**

	Mark Hatle <mark.hatle@amd.com>
	Sandeep Gundlupet Raju <sandeep.gundlupet-raju@amd.com>
	John Toomey <john.toomey@amd.com>
	Trevor Woerner <trevor.woerner@amd.com>
---
## Dependencies

This layer depends on:

	URI: https://git.yoctoproject.org/poky
	layers: meta, meta-poky
	branch: langdale

	URI: https://git.openembedded.org/meta-openembedded
	layers: meta-oe
	branch: langdale

	URI:
        https://git.yoctoproject.org/meta-xilinx (official version)
        https://github.com/Xilinx/meta-xilinx (development and amd xilinx release)
	layers: meta-xilinx-microblaze, meta-xilinx-core, meta-xilinx-standalone, meta-xilinx-standalone-sdt
	branch: langdale or amd xilinx release version (e.g. rel-v2025.1)

	URI:
        https://git.yoctoproject.org/meta-xilinx-tools (official version)
        https://github.com/Xilinx/meta-xilinx-tools (development and amd xilinx release)
	branch: langdale or amd xilinx release version (e.g. rel-v2025.1)

	URI: https://github.com/Xilinx/meta-amd-adaptive-socs
	layers: meta-amd-adaptive-socs-core 
	branch: amd xilinx release version (e.g. rel-v2025.1)

	URI: https://github.com/Xilinx/meta-petalinux
	branch: amd xilinx release version (e.g. rel-v2025.1)

	URI: https://github.com/Xilinx/meta-jupyter
	branch: langdale or amd xilinx release version (e.g. rel-v2025.1)

## License

Copyright (C) 2024, Advanced Micro Devices, Inc.\
SPDX-License-Identifier: MIT
