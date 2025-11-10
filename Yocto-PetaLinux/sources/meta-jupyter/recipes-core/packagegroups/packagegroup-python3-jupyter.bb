DESCRIPTION = "Python packages to build jupyter"

inherit packagegroup python3-dir

RDEPENDS:${PN} = "  \
	python3-jupyterlab \
	python3-jupyter-notebook \
	"
