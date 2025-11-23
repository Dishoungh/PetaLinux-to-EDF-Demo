require recipes-extended/images/container-devtools-base.bb

IMAGE_INSTALL:append = " \
	ca-certificates \
	git \
"
