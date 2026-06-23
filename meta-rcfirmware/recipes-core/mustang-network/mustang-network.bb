SUMMARY = "Apollo Network Configuration for Beelink MiniPC S13 Pro"
LICENSE = "CLOSED"

SRC_URI = " \
    file://wpa_supplicant-wlo1.conf \
    file://80-wlo1.network \
    file://iwlwifi.conf \
"

S = "${WORKDIR}"

do_install() {
    # Install wpa_supplicant config
    install -d ${D}${sysconfdir}/wpa_supplicant
    install -m 0600 ${WORKDIR}/wpa_supplicant-wlo1.conf ${D}${sysconfdir}/wpa_supplicant/

    # Enable wpa_supplicant@wlan0 service to start on boot
    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
    ln -sf ${systemd_unitdir}/system/wpa_supplicant@.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@wlo1.service

    # Install systemd-networkd config for DHCP
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/80-wlo1.network ${D}${sysconfdir}/systemd/network/

    # Fix Intel AX101 Wi-Fi 6 firmware bug by forcing 802.11ac
    install -d ${D}${sysconfdir}/modprobe.d
    install -m 0644 ${WORKDIR}/iwlwifi.conf ${D}${sysconfdir}/modprobe.d/
}

FILES:${PN} = " \
    ${sysconfdir}/wpa_supplicant/wpa_supplicant-wlo1.conf \
    ${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@wlo1.service \
    ${sysconfdir}/systemd/network/80-wlo1.network \
    ${sysconfdir}/modprobe.d/iwlwifi.conf \
"

# Ensure wpa_supplicant is installed alongside our custom configs
RDEPENDS:${PN} += "wpa-supplicant"
