SUMMARY = "OpenSTLinux core image."
LICENSE = "Proprietary"

include recipes-st/images/st-image.inc

inherit core-image

IMAGE_LINGUAS = "en-us"

BOOTDEVICE_LABELS = "nand-2-128-256"

IMAGE_FEATURES += "\
    splash \
    package-management  \
    ssh-server-dropbear \
    "

#
# INSTALL addons
#
CORE_IMAGE_EXTRA_INSTALL += " \
    resize-helper \
    \
    packagegroup-framework-core-base    \
    packagegroup-framework-tools-base   \
    \
    ${@bb.utils.contains('COMBINED_FEATURES', 'optee', 'packagegroup-optee-core', '', d)}   \
    ${@bb.utils.contains('COMBINED_FEATURES', 'optee', 'packagegroup-optee-test', '', d)}   \
    tcpdump \
    udev-extraconf \
    valgrind \
    iperf3 \
    serialcheck \
    ppp \
    gawk \
    vim \
    libmodbus \
    tzdata \
    gdb \
    myir-tool \
    lvgl-demo \
    "
