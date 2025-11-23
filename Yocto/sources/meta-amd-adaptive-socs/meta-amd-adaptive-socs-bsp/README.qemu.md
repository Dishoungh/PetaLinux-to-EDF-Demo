# QEMU Machines

Many of the adaptive soc BSPs in this layer already support qemu, however for
testing purposes specific QEMU machines have been added.  These machines work
with the Yocto Project test infrastructure to allow for better testing of
the produced operating system and their components.

Each of these QEMU machines may or may not be based on real hardware.  No
intention to model actual hardware is intended, but we may use real hardware
(reference boards) to simplify the emulation.

# Requirements

Yocto Project requires at least two serial ports for Linux.  Where possible,
these machines have had multiple serial ports enabled.

The Yocto Project 'qemu.inc' machine configuration will be used.  This
may enable various qemu specific test configurations and should not be used
in a production environment.

# Available machines

| FPGA/SoC Fam | machine              | description                  |
|--------------|----------------------|------------------------------|
| MB-V (32)    | qemu-microblaze-v-32 | 32-bit RISC-V (Microblaze-V) |
| MB-V (64)    | qemu-microblaze-v-64 | 64-bit RISC-V (Microblaze-V) |
