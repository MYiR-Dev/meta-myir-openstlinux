SUMMARY = "Add support of camera preview on Demo Launcher"
HOMEPAGE = "wiki.st.com"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

DEPENDS = "demo-launcher event-gtk-player  wayland-utils"

PV = "2.0"

SRC_URI = " \
    file://Video_playback_logo.png \
    file://ST2297_visionv3.webm \
    file://ST19619_ST_Company_Video_16_9_EN_272p.webm \
    file://launch_video.sh \
    file://020-video.yaml \
    \
    file://video_playback.desktop \
    file://video_playback.png \
    "

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${prefix}/local/demo/gtk-application
    install -d ${D}${prefix}/local/demo/application/video/bin
    install -d ${D}${prefix}/local/demo/application/video/pictures
    install -d ${D}${prefix}/local/demo/media

    # desktop application
    install -d ${D}${datadir}/applications
    install -m 0644 ${WORKDIR}/video_playback.desktop ${D}${datadir}/applications

    # picture for desktop application
    install -d ${D}${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/video_playback.png ${D}${datadir}/pixmaps

    # install yaml file
    install -m 0644 ${WORKDIR}/*.yaml ${D}${prefix}/local/demo/gtk-application/
    # install pictures
    install -m 0644 ${WORKDIR}/*.png ${D}${prefix}/local/demo/application/video/pictures
    # script
    install -m 0755 ${WORKDIR}/*.sh ${D}${prefix}/local/demo/application/video/bin
    # video
    install -m 0644 ${WORKDIR}/ST2297_visionv3.webm ${D}${prefix}/local/demo/media
    install -m 0644 ${WORKDIR}/ST19619_ST_Company_Video_16_9_EN_272p.webm ${D}${prefix}/local/demo/media
}

FILES:${PN} += "${prefix}/local/demo ${prefix}/local/demo/media ${datadir}/applications ${datadir}/pixmaps"
RDEPENDS:${PN} += "demo-launcher event-gtk-player"
