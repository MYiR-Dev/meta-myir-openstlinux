SUMMARY = "OpenSTLinux weston image with basic Wayland support (if enable in distro)."
LICENSE = "Proprietary"

include recipes-st/images/st-image.inc

inherit core-image features_check

# let's make sure we have a good image...
REQUIRED_DISTRO_FEATURES = "wayland"

IMAGE_LINGUAS = "en-us"

IMAGE_FEATURES += "\
    splash              \
    package-management  \
    ssh-server-dropbear \
    hwcodecs            \
    tools-profile       \
    eclipse-debug       \
    "

#IMAGE_ROOTFS_MAXSIZE = "4000000"
# Define ROOTFS_MAXSIZE to 3.5GB
IMAGE_ROOTFS_MAXSIZE = "3670016"
#IMAGE_ROOTFS_SIZE = "3670016"
IMAGE_FSTYPES:remove = "multiubi"
BOOTDEVICE_LABELS = "sdcard"
WKS_FILE_DEPENDS = " \
    virtual/bootloader \
    virtual/trusted-firmware-a \
    ${@bb.utils.contains('BOOTSCHEME_LABELS', 'optee', 'virtual-optee-os', '', d)} \
    ${@bb.utils.contains('ST_BOOTFS','1','st-image-bootfs', '', d)} \
    ${@bb.utils.contains('ST_VENDORFS','1','st-image-vendorfs', '', d)} \
    ${@bb.utils.contains('ST_USERFS','1','st-image-userfs', '', d)} \
"
WKS_IMAGE_FSTYPES += "wic wic.bz2 wic.bmap"
WKS_FILE += "${OPTEE_WIC_FILE}"
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
    gawk \
    vim \
    fac-burn-flash \
    "

# NOTE:
#   packagegroup-st-demo are installed on rootfs to populate the package
#   database.
