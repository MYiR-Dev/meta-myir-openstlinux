SUMMARY = "OpenSTLinux weston image with basic Wayland support (if enable in distro)."
LICENSE = "Proprietary"

include recipes-st/images/st-image.inc

inherit core-image features_check

# let's make sure we have a good image...
REQUIRED_DISTRO_FEATURES = "wayland"

IMAGE_LINGUAS = "en-us"
IMAGE_FSTYPES:remove = "multiubi"

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
    ${@bb.utils.contains('COMBINED_FEATURES', 'optee', 'optee-examples', '', d)} \
    \
    ${@bb.utils.contains('COMBINED_FEATURES', 'tpm2', 'packagegroup-security-tpm2', '', d)} \
    \
    ${@bb.utils.contains('OTA_SUPPORT', '1', '', 'udev-extraconf', d)} \
    valgrind \
    iperf3 \
    serialcheck \
    ppp \
    gawk \
    vim \
    libmodbus \
    python3-pip \
    tzdata \
    gdb \
    myir-tool \
    autorun \
    keyutils \
    lvm2 \
    myir-test-function \
    "

# NOTE:
#   packagegroup-st-demo are installed on rootfs to populate the package
#   database.
remove_unwanted_includes() {
    rm -rf ${IMAGE_ROOTFS}/usr/include/boost
    echo "Removed unwanted include directories from rootfs."
}

ROOTFS_POSTPROCESS_COMMAND += "remove_unwanted_includes; "
