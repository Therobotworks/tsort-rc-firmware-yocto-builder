# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "A simple, easy to use PID controller"
HOMEPAGE = "https://github.com/m-lundberg/simple-pid"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=1a7fa6f25cbb557260d9fe1b40fe08c2"

SRC_URI[sha256sum] = "5771761c54541c900e597d6c2e4ea93fd2cc98f4e48cb9eca265fba2e65be99e"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "simple_pid"
