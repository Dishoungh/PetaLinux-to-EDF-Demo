DESCRIPTION = "Generate Embedded-Plus Adaptive Management Controller (AMC) application image"
SUMMARY = "Adaptive Management Runtime(AMR) compoment"

inherit python3native ccmake cmake deploy

require amr.inc

COMPATIBLE_HOST:arm = "[^-]*-[^-]*-eabi"

DEPENDS += "libxil xilstandalone xiltimer freertos10-xilinx xilmailbox xilloader"

S = "${WORKDIR}/git/fw/AMC"
B = "${WORKDIR}/build"
OECMAKE_SOURCEPATH = "${S}"

CFLAGS:append = " -specs=${PKG_CONFIG_SYSROOT_DIR}/usr/include/Xilinx.spec -DSDT "

EXTRA_OECMAKE:append = " \
    -DCMAKE_LIBRARY_PATH=${PKG_CONFIG_SYSROOT_DIR}/usr/lib/ \
    -DYOCTO=ON \
    "
do_configure[depends] += " \
    freertos10-xilinx:do_install \
    xiltimer:do_install \
    xilplmi:do_install \
    xilloader:do_install \
    "

# Generate config file before generating toolchain file
cmake_do_generate_toolchain_file:append:arm() {
    cat >> ${WORKDIR}/toolchain.cmake <<EOF
    include (CMakeForceCompiler)
    CMAKE_FORCE_C_COMPILER("${OECMAKE_C_COMPILER}" GNU)
    CMAKE_FORCE_CXX_COMPILER("${OECMAKE_CXX_COMPILER}" GNU)
    set (CMAKE_SYSTEM_PROCESSOR "${TRANSLATED_TARGET_ARCH}" )
    set (CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} ")
    set (CMAKE_LIBRARY_PATH "${CMAKE_LIBRARY_PATH};${PKG_CONFIG_SYSROOT_DIR}/usr/lib" CACHE PATH "" FORCE)

    set (CMAKE_FIND_ROOT_PATH "${CMAKE_FIND_ROOT_PATH} ${STAGING_LIBDIR} ${CMAKE_INCLUDE_PATH} " CACHE STRING "")
    set (CMAKE_INCLUDE_PATH "${CMAKE_INCLUDE_PATH} ${PKG_CONFIG_SYSROOT_DIR}/usr/include/" CACHE STRING "")
    set (XIL_INCLUDE_DIR " ${PKG_CONFIG_SYSROOT_DIR}/usr/include/" CACHE STRING "")
    set (CMAKE_C_FLAGS " ${CMAKE_C_FLAGS} ${PKG_CONFIG_SYSROOT_DIR}/usr/include/" CACHE STRING "")
EOF
}

do_deploy() {
    install -Dm 644 ${B}/amc.elf ${DEPLOYDIR}/${PN}.elf
    ln -sf ${PN}.elf ${DEPLOYDIR}/${PN}-${MACHINE}.elf
}

addtask deploy after do_compile
