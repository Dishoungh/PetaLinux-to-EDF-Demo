# Build Instructions

This section describes how to get your build host ready to work with meta-amd-adaptive-socs
layers.

The following instructions require OE-Core meta and BitBake. Poky provides these
components, however they can be acquired separately.

> **Pre-requisites:** See [Preparing Build Host](https://docs.yoctoproject.org/5.0.8/singleindex.html#preparing-the-build-host) documentation.

1. Follow [Building Instructions](https://github.com/Xilinx/meta-xilinx/blob/master/README.building.md)
   upto step 5.

2. Clone meta-amd-adaptive-socs and dependency repository.
```
$ cd ../sources
$ git clone -b <rel-version> https://gitenterprise.xilinx.com/Yocto/meta-amd-adaptive-socs
$ cd -
```

3. Add meta-amd-adaptive-socs-core and meta-amd-adaptive-socs-bsp layers.
```
$ bitbake-layers add-layer ./<path-to-layer>/meta-amd-adaptive-socs/meta-amd-adaptive-socs-core
$ bitbake-layers add-layer ./<path-to-layer>/meta-amd-adaptive-socs/meta-amd-adaptive-socs-bsp
```

4. Continue [Building Instructions]([../README.building.md](https://github.com/Xilinx/meta-xilinx/blob/master/README.building.md)) from step 5(skip step 7).

* For list of available SDT target machines see meta layer conf/machine/*.conf file.

  * [meta-amd-adaptive-socs-bsp machine conf file](meta-amd-adaptive-socs-bsp/conf/machine)
