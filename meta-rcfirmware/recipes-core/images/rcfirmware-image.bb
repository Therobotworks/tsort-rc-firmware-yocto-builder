SUMMARY = "Production tSort Robot Control(RC) Firmware Yocto Image for Beelink MiniPC S13 Pro"
LICENSE = "CLOSED" 

require recipes-core/images/ros-image-core.bb

# Generate a WIC image for flashing to SD/eMMC
IMAGE_FSTYPES += "wic"


# Enable SSH server
IMAGE_FEATURES += "ssh-server-openssh"

# Inherit extrausers to handle the 'usermod' command
inherit extrausers

# Add the user to the required hardware groups
EXTRA_USERS_PARAMS = " \
    useradd -p '' mustang; \
    usermod -a -G dialout,video,plugdev,sudo mustang; \
    usermod -p '\$6\$2o7on.Og9.3M3eI2\$6wmrXofrDEoNTVHFv6qoe6MeEb9RyHOR6iH4ogrQSlemhC4UBHH/DqIiU6E7XaiD6e4UJx4GHhyupZr1FGcmY/' mustang; \
"

# Install core packages
IMAGE_INSTALL:append = " \
    packagegroup-core-buildessential \
    python3 \
    python3-pip \
    glibc-localedata-en-us \
    curl \
    opencv \
    libserial \
    python3-ruamel-yaml \
    zxing-cpp \
    tsort-rc-firmware-cpp \
    tsort-rc-firmware-py \
    mustang-ignition \
    sudo \
    iw \
    mustang-network \
    kernel-module-uvcvideo \
    kernel-module-cdc-acm \
    firmware-nxp-wifi-nxp8997-pcie \
    kernel-module-nxp-wlan \
    systemd \
    \
    parted \
    e2fsprogs \
    dosfstools \
    util-linux \
"

# Define what the 'sudo' group is allowed to do (Read this first)
setup_sudo_group() {
    install -d ${IMAGE_ROOTFS}/etc/sudoers.d
    echo '%sudo ALL=(ALL) ALL' > ${IMAGE_ROOTFS}/etc/sudoers.d/10-sudo-group-rule
    chmod 0440 ${IMAGE_ROOTFS}/etc/sudoers.d/10-sudo-group-rule
}

# Replicate the sudoers rule for passwordless shutdown (Read this last so it overrides)
setup_mustang_sudoers() {
    install -d ${IMAGE_ROOTFS}/etc/sudoers.d
    echo 'mustang ALL=(ALL) NOPASSWD: /usr/sbin/shutdown' > ${IMAGE_ROOTFS}/etc/sudoers.d/90-mustang-shutdown
    chmod 0440 ${IMAGE_ROOTFS}/etc/sudoers.d/90-mustang-shutdown
}

# Run the functions during the final filesystem creation phase
ROOTFS_POSTPROCESS_COMMAND += "setup_sudo_group; "
ROOTFS_POSTPROCESS_COMMAND += "setup_mustang_sudoers; "
