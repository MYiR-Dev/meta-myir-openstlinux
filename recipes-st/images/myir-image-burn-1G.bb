SUMMARY = "OpenSTLinux weston image with basic Wayland support (if enable in distro)."
LICENSE = "Proprietary"

include recipes-st/images/st-image.inc

inherit core-image features_check
#inherit populate_sdk populate_sdk_qt5

# let's make sure we have a good image...
REQUIRED_DISTRO_FEATURES = "wayland"

#IMAGE_LINGUAS = "en-us"
#IMAGE_LINGUAS = "zh-cn"
#DEFAULT_IMAGE_LINGUAS:libc-glibc += "zh-cn"
GLIBC_GENERATE_LOCALES = "zh_CN.UTF-8 en_GB.UTF-8 en_US.UTF-8"
IMAGE_LINGUAS ?= "zh-cn"

IMAGE_FEATURES += "\
    splash              \
    package-management  \
    ssh-server-dropbear \
    hwcodecs            \
    tools-profile       \
    eclipse-debug       \
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
    packagegroup-framework-core         \
    packagegroup-framework-tools        \
    \
    packagegroup-framework-core-extra   \
    \
    ${@bb.utils.contains('COMBINED_FEATURES', 'optee', 'packagegroup-optee-core', '', d)} \
    ${@bb.utils.contains('COMBINED_FEATURES', 'optee', 'packagegroup-optee-test', '', d)} \
    \
    ${@bb.utils.contains('COMBINED_FEATURES', 'tpm2', 'packagegroup-security-tpm2', '', d)} \
    \
   valgrind \
   iperf3 \
   serialcheck \
   ppp \
   gawk \
   vim \
   libmodbus \
    myir-tool \
    event-gtk-player \
    eeprom-pnsn \
    kexec-tools \
    portaudio-v19 \
    ppp-quectel \
    quectel-cm \
    autorun \
    fac-burn-flash-1g \
    "

# NOTE:
#   packagegroup-st-demo are installed on rootfs to populate the package
#   database.
