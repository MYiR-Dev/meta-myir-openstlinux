# Copyright (C) 2018, STMicroelectronics - All Rights Reserved

SUMMARY = "Script which start a launcher"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI = " \
    file://start_up_demo_launcher.sh \
    "

PV = "2.3"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${prefix}/local/demo/
    install -m 0755 ${WORKDIR}/start_up_demo_launcher.sh ${D}${prefix}/local/demo/
}

FILES:${PN} += "${prefix}/local/demo/start_up_demo_launcher.sh"
