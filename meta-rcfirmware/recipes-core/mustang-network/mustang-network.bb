SUMMARY = "Apollo Network Configuration for Beelink MiniPC S13 Pro"
LICENSE = "CLOSED"

SRC_URI = " \
    file://wpa_supplicant-mlan0.conf \
    file://80-wifi.network \
    file://70-nxp-wifi.link \
    file://moal.conf \
"

S = "${WORKDIR}"

do_install() {
    # Install wpa_supplicant config
    install -d ${D}${sysconfdir}/wpa_supplicant
    install -m 0600 ${WORKDIR}/wpa_supplicant-mlan0.conf ${D}${sysconfdir}/wpa_supplicant/

    # Enable wpa_supplicant@mlan0 service to start on boot
    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
    ln -sf ${systemd_unitdir}/system/wpa_supplicant@.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@mlan0.service

    # Install systemd-networkd configs (DHCP and link rename prevention)
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/80-wifi.network ${D}${sysconfdir}/systemd/network/
    install -m 0644 ${WORKDIR}/70-nxp-wifi.link ${D}${sysconfdir}/systemd/network/

    # Install modprobe config for NXP Wi-Fi
    install -d ${D}${sysconfdir}/modprobe.d
    install -m 0644 ${WORKDIR}/moal.conf ${D}${sysconfdir}/modprobe.d/
}

FILES:${PN} = " \
    ${sysconfdir}/wpa_supplicant/wpa_supplicant-mlan0.conf \
    ${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@mlan0.service \
    ${sysconfdir}/systemd/network/80-wifi.network \
    ${sysconfdir}/systemd/network/70-nxp-wifi.link \
    ${sysconfdir}/modprobe.d/moal.conf \
"

# Ensure wpa_supplicant is installed alongside our custom configs
RDEPENDS:${PN} += "wpa-supplicant"
