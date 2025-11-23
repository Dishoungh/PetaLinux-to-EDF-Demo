# Both arm and armv*r overrides are set w/ cortex r5
# So only set rmprofile if armv*r is defined.
ARM_PROFILE = "aprofile"
ARM_PROFILE:armv7r = "rmprofile"
ARM_PROFILE:armv8r = "rmprofile"
EXTRA_OECONF:append:xilinx-standalone:arm:baremetal-multilib-tc = " \
        --with-multilib-list=${ARM_PROFILE} \
        "

# RISC V configuration
RISCV_MULTILIB = "\
rv32i-ilp32--a*zicsr*zifencei;\
rv32ib-ilp32--a*zicsr*zifencei;\
rv32ic-ilp32--a*zicsr*zifencei;\
rv32icb-ilp32--a*zicsr*zifencei;\
rv32im-ilp32--a*zicsr*zifencei;\
rv32imb-ilp32--a*zicsr*zifencei;\
rv32imc-ilp32--zicsr*zifencei;\
rv32imcb-ilp32--a*zicsr*zifencei;\
rv32imac-ilp32--b;\
rv32imac_zicsr_zifencei-ilp32--b;\
rv32imf_zicsr-ilp32f--b*a*zicsr*zifencei;\
rv32imfc_zicsr-ilp32f--b*zifencei;\
rv32imafc_zicsr-ilp32f--b*zifencei;\
rv32imfdc_zicsr-ilp32d--b*a*zifencei;\
rv64i-lp64--b*a*zicsr*zifencei;\
rv64ic-lp64--b*a*zicsr*zifencei;\
rv64im-lp64--b*a*zicsr*zifencei;\
rv64imc-lp64--b*zicsr*zifencei;\
rv64imac-lp64--b*zicsr*zifencei;\
rv64imf_zicsr-lp64f-rv64imf-b*a*zifencei;\
rv64imfc_zicsr-lp64f-rv64imfc-b*zifencei;\
rv64imafc_zicsr-lp64f-rv64imafc-b*zifencei;\
rv64imfdc_zicsr-lp64d-rv64imfdc-b*a*zifencei\
"

EXTRA_OECONF:append:xilinx-standalone:riscv32:baremetal-multilib-tc = " \
	--with-multilib-generator='${RISCV_MULTILIB}' \
	"
EXTRA_OECONF:append:xilinx-standalone:riscv64:baremetal-multilib-tc = " \
	--with-multilib-generator='${RISCV_MULTILIB}' \
	"
