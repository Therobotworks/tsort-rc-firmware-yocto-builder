SUMMARY = "RC Firmware ROS2 Python Nodes"
LICENSE = "CLOSED"

SRC_URI = "git://github.com/Therobotworks/tsort-rc-firmware.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/mustangcontrol_py"

inherit ros_ament_python

# Fix broken variable expansion from ros_ament_python
PYTHON_SITEPACKAGES_DIR = "/usr/lib/python3.12/site-packages"

RDEPENDS:${PN} += " \
    rclpy \
    python3-core \
    python3-simple-pid \
    python3-websocket-client \
"

FILES:${PN} += " \
    /opt/ros/jazzy/lib/mustangcontrol_py \
    /opt/ros/jazzy/lib/mustangcontrol_py/* \
    /opt/ros/jazzy/share/mustangcontrol_py \
    /opt/ros/jazzy/share/mustangcontrol_py/* \
    /opt/ros/jazzy/share/ament_index/resource_index/packages/mustangcontrol_py \
    ${PYTHON_SITEPACKAGES_DIR}/mustangcontrol_py \
    ${PYTHON_SITEPACKAGES_DIR}/mustangcontrol_py/* \
    ${PYTHON_SITEPACKAGES_DIR}/mustangcontrol_py/__pycache__ \
    ${PYTHON_SITEPACKAGES_DIR}/mustangcontrol_py/__pycache__/* \
    ${PYTHON_SITEPACKAGES_DIR}/mustangcontrol_py-0.0.0-py3.12.egg-info \
    ${PYTHON_SITEPACKAGES_DIR}/mustangcontrol_py-0.0.0-py3.12.egg-info/* \
"

do_install:append() {
    if [ -d "${D}/lib/mustangcontrol_py" ]; then
        install -d ${D}/opt/ros/jazzy/lib/mustangcontrol_py
        cp -r ${D}/lib/mustangcontrol_py/* ${D}/opt/ros/jazzy/lib/mustangcontrol_py/
        rm -rf ${D}/lib
    fi

    for script in ${D}/opt/ros/jazzy/lib/mustangcontrol_py/*; do
        if [ -f "$script" ]; then
            sed -i -e '1s|^#!.*|#!/usr/bin/env python3|' "$script"
        fi
    done

    if [ -d "${D}/python3.12" ]; then
        install -d ${D}/usr/lib/python3.12
        cp -r ${D}/python3.12/site-packages ${D}/usr/lib/python3.12/
        rm -rf ${D}/python3.12
    fi

    if [ -d "${D}/ament_index" ]; then
        install -d ${D}/opt/ros/jazzy/share/ament_index/resource_index/packages
        cp -r ${D}/ament_index/resource_index/packages/* \
              ${D}/opt/ros/jazzy/share/ament_index/resource_index/packages/
        rm -rf ${D}/ament_index
    fi

    if [ -d "${D}/mustangcontrol_py" ]; then
        install -d ${D}/opt/ros/jazzy/share/mustangcontrol_py
        cp -r ${D}/mustangcontrol_py/* ${D}/opt/ros/jazzy/share/mustangcontrol_py/
        rm -rf ${D}/mustangcontrol_py
    fi
}
