# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Socket.IO server and client for Python"
HOMEPAGE = "https://github.com/miguelgrinberg/python-socketio"
# NOTE: License in pyproject.toml is: MIT
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=42d0a9e728978f0eeb759c3be91536b8"

SRC_URI[sha256sum] = "f863f98eacce81ceea2e742f6388e10ca3cdd0764be21d30d5196470edf5ea89"

inherit pypi python_setuptools_build_meta


# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
#    bidict
#    python-engineio

PYPI_PACKAGE = "python_socketio"
