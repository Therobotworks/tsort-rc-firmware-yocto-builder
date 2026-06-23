SUMMARY = "RC Firmware ROS2 C++ Nodes"
LICENSE = "CLOSED"

# Download the whole repository
SRC_URI = "git://github.com/Therobotworks/tsort-rc-firmware.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/mustangcontrol_cpp"

ROS_BPN = "mustangcontrol_cpp"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
    rosidl-default-generators-native \
"

ROS_BUILD_DEPENDS = " \
    ament-cmake \
    rclcpp \
    std-msgs \
    geometry-msgs \
    sensor-msgs \
    opencv \
    rosidl-default-runtime \
    libserial \
    zxing-cpp \
"

ROS_EXEC_DEPENDS = " \
    rclcpp \
    std-msgs \
    geometry-msgs \
    sensor-msgs \
    opencv \
    rosidl-default-runtime \
    libserial \
    zxing-cpp \
"

# Map the ROS variables to the standard Yocto variables
DEPENDS = "${ROS_BUILDTOOL_DEPENDS} ${ROS_BUILD_DEPENDS}"
RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

inherit pkgconfig ros_opt_prefix ros_component ros_ament_cmake

# Let Yocto manage the internal search paths naturally. Only disable testing.
EXTRA_OECMAKE += "-DBUILD_TESTING=OFF"
