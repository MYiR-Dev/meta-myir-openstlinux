SUMMARY = "OpenSTLinux core image."
LICENSE = "Proprietary"

include recipes-st/images/st-image.inc

inherit core-image

#IMAGE_LINGUAS = "en-us"
GLIBC_GENERATE_LOCALES = "zh_CN.UTF-8 en_GB.UTF-8 en_US.UTF-8"
IMAGE_LINGUAS ?= "zh-cn"

IMAGE_FEATURES += "\
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
    python3-pip \
    myir-tool \
    event-gtk-player \
    eeprom-pnsn \
    kexec-tools \
    portaudio-v19 \ 
    ppp-quectel \
    quectel-cm \
    "
