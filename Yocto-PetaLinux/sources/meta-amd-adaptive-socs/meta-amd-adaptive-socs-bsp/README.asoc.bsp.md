# AMD Adaptive SoC's Evaluation Boards SDT BSP Machines files

The following boards are supported by the meta-amd-adaptive-socs-bsp layer. Eval
board SDT BSP machine configuration files are generated using
meta-amd-adaptive-socs/meta-amd-adaptive-socs-bsp/scripts/generate-machines-sdt.sh
scripts.

> **SDT BSP Machine nomenclature:**
>
> 1. Machine Configuration file nomenclature: `<soc-family>-<eval-board-name>-sdt-<design-name>`
> * Example: `MACHINE = "versal-vek280-sdt-seg"`
>
> 2. BSP Reference design name:
> * `full` - ZynqMP full bitstream loading Vivado design.
> * `seg` - Versal Segmented Configuration Vivado design.
>
> **Note:** In machine file nomencalutre `<soc-family>-<eval-board-name>-sdt-<design-name>`
> If design-name suffix is not set or defined then it is treated as flat design
> without dynamic PL configuration.

| Devices | Evaluation Board  | Machine Configuration file | Reference Design | QEMU tested | HW tested |
|---------|-------------------|----------------------------|------------------|-------------|-----------|
| ZynqMP  | [ZCU104](https://www.amd.com/en/products/adaptive-socs-and-fpgas/evaluation-boards/zcu104.html) | [zynqmp-zcu104-sdt-full](conf/machine/zynqmp-zcu104-sdt-full.conf) | `full`| Yes | Yes |
|         | [ZCU111](https://www.amd.com/en/products/adaptive-socs-and-fpgas/evaluation-boards/zcu111.html) | [zynqmp-zcu111-sdt-full](conf/machine/zynqmp-zcu111-sdt-full.conf) | `full`| Yes | Yes |
| Versal  | [VEK280](https://www.xilinx.com/products/boards-and-kits/vek280.html) | [versal-vek280-sdt-seg](conf/machine/versal-vek280-sdt-seg.conf)   | `seg` | Yes | Yes |

> **Note:** Additional information on AMD Adaptive SoC's and FPGA's can be found at:
	https://www.amd.com/en/products/adaptive-socs-and-fpgas.html
