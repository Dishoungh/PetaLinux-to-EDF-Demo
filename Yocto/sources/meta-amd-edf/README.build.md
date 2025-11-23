# Build Instructions

This section describes how to get your build host ready to work with meta-amd-edf
layers.

## Yocto AMD Embedded Development Framework (EDF) build setup instructions

The following instructions require OE-Core meta and BitBake. Poky provides these
components, however they can be acquired separately.

> **Pre-requisites:**
> 1. See [Preparing Build Host](https://docs.yoctoproject.org/5.0.8/singleindex.html#preparing-the-build-host) documentation.
> 2. Configure the git settings before you run the repo commands.
>     ```
>     $ git config --global user.email "you@example.com"
>     $ git config --global user.name "Your Name"
>     ```
> 3. Make sure build host shell is bash not csh or dash.
> 4. A basic understanding of the Yocto build system is assumed - please consult the documentation for further information https://docs.yoctoproject.org/


1. Download and install Repo tool (if it wasn't installed in a previous step).
   Note that if you have repo installed through a package manager that should be
   removed first as it is likely out of date and will cause issues.

   ```
   $ curl https://storage.googleapis.com/git-repo-downloads/repo > repo
   $ chmod a+x repo
   $ mv repo ~/bin/
   $ PATH=~/bin:$PATH
   $ repo --help
   ```
2. Initialize a Repo client.
   1. Create the edf project directory.
      ```
      $ mkdir -p yocto/<release_version>/edf
      $ cd yocto/<release_version>/edf
      ```
   2. Clone the Yocto meta layer source using yocto manifest as show below. A
      successful initialization will end with a message stating that Repo is
      initialized in your working directory. Your directory should now contain a
      .repo directory where repo control files such as the manifest are stored
      but you should not need to touch this directory.
      To learn more about repo, look at https://source.android.com/setup/develop/repo
      ```
      $ repo init -u https://github.com/Xilinx/yocto-manifests.git -b <release_version> -m default-edf.xml
      ```

3. Fetch all the repositories.
```
$ repo sync
```

4. Start a branch with for development starting from the revision specified in
   the manifest. This is an optional step.
```
$ repo start <branch_name> --all
```

5. Initialize a build environment by sourcing the `edf-init-build-env` script.
```
$ source edf-init-build-env
```
> **Note:** 
> 1. This expects you are using the repo configuration from earlier steps. This
>    script is simply automating the Yocto Project workflow steps.
> 2. If you have already set a TEMPLATECONF from previous instructions, make sure
>    you unset it before sourcing the internal-edf-init-build-env script else
>    you will run into templateconf errors.
>    `Error: TEMPLATECONF value points to nonexistent directory '<template-path>'`

6. Once environment is initialized `bblayers.conf` add `local.conf` are set from
   meta-amd-edf templates.

7. For list of available board machines see meta-adaptive-socs/meta-amd-<eval-board>/conf/machine/*.conf
   file.

8. For NFS build host system modify the build/conf/local.conf and add TMPDIR
   path as shown below. On local storage $TMPDIR will be set to build/tmp
   ```
   TMPDIR = "/tmp/$USER/yocto/release_version/build"
   ```
> **Note:**
> Depending on the build configuration, several 10s of GB of storage space may
> be required in the TMPDIR. Please make sure you have plenty of storage space
> available. Alternatively, to save disk space, you can add the below varibale to
> build/conf/local.conf file. This option removes the work dir after build and only
> keeps the logs around.
>  ```
>  INHERIT += "rm_work"
>  ```

9. Build the qemu-helper-native package to setup QEMU network tap devices.
   ```
   $ bitbake qemu-helper-native
   ```
> **Note:**
>  1. If user doesn't have sudo permissions on build host you can skip step 9 and
>     10.
>  2. To build qemu-helper-native recipes you can use any one of the
>     amd-<tunearch>-common machine name. Here is the list of supported common
>     machines https://github.com/Xilinx/meta-xilinx/tree/rel-v2025.2/meta-xilinx-core/conf/machine

10. Manually configure a tap interface for your build system. As root run
   <path-to>/sources/poky/scripts/runqemu-gen-tapdevs, which should generate a
   list of tap devices. Once tap interfaces are successfully create you should
   be able to see all the interfaces by running ifconfig command.
      ```
      $ sudo ./<path-to-layer>/poky/scripts/runqemu-gen-tapdevs $(id -g $USER) 4
      ```


## EDF Image Build using prebuilt Machines

1. Build Yocto pre-requisites by following above instructions.
2. Build the Boot.bin image
   1. ZynqMP(zcu104/zcu111), Versal(vek280) boot.bin
      ```
      $ MACHINE=zynqmp-zcu104-sdt-full bitbake xilinx-bootbin
      or
      $ MACHINE=zynqmp-zcu111-sdt-full bitbake xilinx-bootbin
      or
      $ MACHINE=versal-vek280-sdt-seg bitbake xilinx-bootbin
      ```

3. Build SoC Common disk image (wic) containing general purpose (GP) Linux.
   1. amd-cortexa53-common (zynqmp cg/dr family)
      ```
      $ MACHINE=amd-cortexa53-common bitbake edf-linux-disk-image
      ```
   2. amd-cortexa53-mali-common (zynqmp eg/ev family)
      ```
      $ MACHINE=amd-cortexa53-mali-common bitbake edf-linux-disk-image
      ```
   3. amd-cortexa72-common (versal family)
      ```
      $ MACHINE=amd-cortexa72-common bitbake edf-linux-disk-image
      ```
   4. amd-cortexa78-mali-common (versal-2ve-2vm family)
      ```
      $ MACHINE=amd-cortexa78-mali-common bitbake edf-linux-disk-image
      ```

4. Once complete the images for the target machine will be available in the output
   directory `${TMPDIR}/deploy/images/${MACHINE}/`.

5. Follow [Booting Instructions](https://github.com/Xilinx/meta-xilinx/blob/master/README.booting.md)


## Release Information

Refer [AMD Xilinx Yocto wiki](https://xilinx-wiki.atlassian.net/wiki/spaces/A/pages/2613018625)
page for release features, known issue and limitations.

## Additional Documentation

1. For more information about [Yocto Project](https://www.yoctoproject.org) see Yocto Project docs which can be found at:
 * https://docs.yoctoproject.org/singleindex.html

2. https://xilinx-wiki.atlassian.net/wiki/spaces/A/pages/3250585601/GA+-+AMD+Embedded+Development+Framework+EDF
