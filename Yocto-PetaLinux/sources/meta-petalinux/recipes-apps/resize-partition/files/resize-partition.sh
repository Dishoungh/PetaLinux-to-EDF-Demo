#!/bin/sh

# Expand the sd card partition to 100% from unpartitioned space.
#
# Usage: resize-part "/dev/mmcblk0 p2"

partition="/dev/mmcblk0 p2"

echo "Expanding the Partition:${partition} to 100%"
/usr/bin/resize-part ${partition}
