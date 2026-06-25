#!/bin/bash

# Exit on any error
set -e

echo "Starting Yocto project setup..."

# Function to clone and checkout a specific commit
clone_and_checkout() {
    local repo_url=$1
    local dir_name=$2
    local branch=$3
    local commit_hash=$4

    if [ ! -d "$dir_name" ]; then
        echo "Cloning $dir_name..."
        git clone -b "$branch" "$repo_url" "$dir_name"
        echo "Checking out commit $commit_hash in $dir_name..."
        (cd "$dir_name" && git checkout "$commit_hash")
    else
        echo "$dir_name already exists. Skipping clone."
    fi
}

# Clone required repositories with their specific commits
clone_and_checkout "git://git.yoctoproject.org/poky.git" "poky" "scarthgap" "b56134ff90dec996e8aae45ed7e6ad6b1d5a84a0"
clone_and_checkout "https://git.yoctoproject.org/git/meta-freescale" "poky/meta-freescale" "scarthgap" "HEAD"
clone_and_checkout "git://git.openembedded.org/meta-openembedded" "meta-openembedded" "scarthgap" "d8cc4e44001c7257273d290ce8c4496e93d32841"
clone_and_checkout "https://github.com/ros/meta-ros.git" "meta-ros" "scarthgap" "faba0b8658b1c2416f2380cd1671daa60ced6481"

# Setup the build environment
echo "Initializing the build environment..."
source poky/oe-init-build-env build-imx8mplus

# Return to the root directory
cd ..

# Copy custom configurations
echo "Copying custom configurations..."
cp custom-conf/local.conf build-imx8mplus/conf/local.conf
cp custom-conf/bblayers.conf build-imx8mplus/conf/bblayers.conf

echo ""
echo "Setup complete!"
echo "To start building, activate the environment by running:"
echo "  source poky/oe-init-build-env build-imx8mplus"
echo "Then run bitbake with your recipe, e.g.:"
echo "  bitbake rcfirmware-image"
