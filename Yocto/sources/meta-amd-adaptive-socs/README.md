# meta-amd-adaptive-socs

Collection of layers to enable AMD Apdative SoC's bsp cache metadata.

* **meta-amd-adaptive-socs-core**: layer containing AMD Adaptive SoC's core features.
This layer enables AMD adaptive socs core metadata features such as recipes for
cache output of system device tree generator(sdtgen), system device tree files,
boot pdi and common image features etc.

* **meta-amd-adaptive-socs-bsp**: layer containing the AMD Adaptive SoC's bsp features
such as evaluation board system device tree build metadata such as machine configurations
files, multiconfig files, system device tree files, boot pdi, kernel configuration
fragments, series configuration compiler(.scc) files etc. This layer also supports
to enable Xen hypervisor.

> **Note:** Additional information on AMD Adaptive SoC's and FPGA's can be found at:
	https://www.amd.com/en/products/adaptive-socs-and-fpgas.html

Please see the respective READMEs and docs in the layer subdirectories.

## Maintainers, Mailing list, Patches

Please send any patches, pull requests, comments or questions for this layer to
the [meta-xilinx mailing list](https://lists.yoctoproject.org/g/meta-xilinx)
with ['meta-amd-adaptive-socs'] in the subject:

	meta-xilinx@lists.yoctoproject.org

When sending patches, please make sure the email subject line includes
`[meta-amd-adaptive-socs][<BRANCH_NAME>][PATCH]` and cc'ing the maintainers.

For more details follow the Yocto Project community patch submission guidelines,
as described in:

https://docs.yoctoproject.org/dev/contributor-guide/submit-changes.html#

`git send-email --to meta-xilinx@lists.yoctoproject.org *.patch`

> **Note:** When creating patches, please use below format. To follow best practice,
> if you have more than one patch use `--cover-letter` option while generating the
> patches. Edit the 0000-cover-letter.patch and change the title and top of the
> body as appropriate.

**Syntax:**
`git format-patch -s --subject-prefix="meta-amd-adaptive-socs][<BRANCH_NAME>][PATCH" -1`

**Example:**
`git format-patch -s --subject-prefix="meta-amd-adaptive-socs][scarthgap][PATCH" -1`

**Maintainers:**

	Mark Hatle <mark.hatle@amd.com>
	Sandeep Gundlupet Raju <sandeep.gundlupet-raju@amd.com>
	John Toomey <john.toomey@amd.com>
    Trevor Woerner <trevor.woerner@amd.com>
---

## Additional Documentation

* [Building Instructions](README.build.md)
* [AMD Yocto layers](https://github.com/Xilinx/meta-xilinx/blob/master/README.md)
