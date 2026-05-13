SUMMARY = "OpenSTLinux core image."
LICENSE = "Proprietary"

include recipes-st/images/st-image.inc

inherit core-image

IMAGE_LINGUAS = "en-us"

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
OPTEE_WIC_FILE = "${@bb.utils.contains('ST_VENDORFS','1','sdcard-myd-yf13x-optee-vendorfs-512n.wks.in','sdcard-stm32mp135f-dk-optee-1GB.wks.in',d)}"

ST_OPTEE_EXPORT_TA_REF_BOARD:stm32mp1common = "myb-stm32mp135x-512n512d.dts"

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
    valgrind \
    iperf3 \
    serialcheck \
    ppp \
    gawk \
    vim \
    libmodbus \
    tzdata \
    mtd-utils-ubifs \
    myir-tool \
    fac-burn-flash-nand-512m \
    lvgl-demo \
    "
