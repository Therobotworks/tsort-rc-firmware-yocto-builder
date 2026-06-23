SUMMARY = "Startup Boot Service MustangIgnition"
LICENSE = "CLOSED"

inherit systemd

SRC_URI = " \
    file://mustangignition.service \
    file://setup_robot.bash \
"

S = "${WORKDIR}"

# Tell Yocto which service file to enable
SYSTEMD_SERVICE:${PN} = "mustangignition.service"
SYSTEMD_AUTO_ENABLE = "enable"

# Define where you want the script to live
RDEPENDS:${PN} += "bash"

do_install() {
    # Install the setup script
    install -d ${D}/etc/robot_boot
    install -m 0755 ${WORKDIR}/setup_robot.bash ${D}/etc/robot_boot/

    # Install the systemd service
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/mustangignition.service ${D}${systemd_system_unitdir}
}

FILES:${PN} += " \
    /etc/robot_boot/setup_robot.bash \
    ${systemd_system_unitdir}/mustangignition.service \
"
