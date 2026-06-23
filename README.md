# TSort RC Firmware Yocto Builder

This repository contains the build environment setup and custom Yocto recipes (`meta-rcfirmware`) to build the tSort Robot Controller (RC) firmware image for the Beelink MiniPC S13 Pro. 

To keep the repository clean, large downloaded dependencies like Yocto/Poky, Meta-OpenEmbedded, and Meta-ROS are ignored by Git. A setup script is provided to fetch these dependencies automatically.

## Setup Instructions

### 1. Clone the Repository
Clone this repository to your local machine:
```bash
git clone <repository-url> tsort-rc-firmware-yocto-builder
cd tsort-rc-firmware-yocto-builder
```

### 2. Run the Setup Script
To download all necessary Yocto layers and configure the environment, run the provided setup script. It will clone `poky`, `meta-openembedded`, and `meta-ros` at the correct, tested commit versions, and it will set up the build directory.

```bash
./setup_project.sh
```

### 3. Activate the Environment
After running the setup script, you must activate the Yocto build environment before running any BitBake commands:

```bash
source poky/oe-init-build-env build-n150
```

### 4. Build
You can now build the firmware image:

```bash
bitbake rcfirmware
```

## Repository Structure & Custom Settings

- `custom-conf/`: Contains custom configuration files (`local.conf` and `bblayers.conf`). The `local.conf` is pre-configured specifically for targeting the **Intel N150 processor** and contains all specific tweaks necessary for this project. These files are automatically copied into the `build-n150/conf/` directory by the `setup_project.sh` script.
- `meta-rcfirmware/`: Contains custom Yocto recipes for the tSort RC firmware. This is explicitly tracked by the repository.
- `setup_project.sh`: Fetches dependencies and wires up the `custom-conf` to the downloaded Poky environment.
