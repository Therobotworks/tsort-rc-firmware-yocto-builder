# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "WebSocket client for Python with low level API options"
HOMEPAGE = "https://github.com/websocket-client/websocket-client.git"
# NOTE: License in setup.py/PKGINFO is: Apache-2.0
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b8d4a5e03977c68fad62beee8185704e"

SRC_URI[sha256sum] = "9e813624b6eb619999a97dc7958469217c3176312b3a16a4bd1bc7e08a46ec98"

inherit pypi setuptools3

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
# The configs are might not correct, since PACKAGECONFIG does not support expressions as may used in requires.txt - they are just replaced by text.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= "docs optional test"
PACKAGECONFIG[docs] = ",,,python3-sphinx python3-myst-parser python3-sphinx_rtd_theme"
PACKAGECONFIG[optional] = ",,,python3-python-socks python3-wsaccel"
PACKAGECONFIG[test] = ",,,python3-pytest python3-websockets"


# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "python3-core"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    argparse
#    asyncio
#    base64
#    code
#    gzip
#    hashlib
#    hmac
#    http
#    http.cookies
#    inspect
#    ipaddress
#    logging
#    os
#    python_socks._errors
#    python_socks._types
#    python_socks.sync
#    readline
#    selectors
#    socket
#    ssl
#    struct
#    threading
#    typing
#    unittest
#    unittest.mock
#    urllib.parse
#    wsaccel.utf8validator
#    wsaccel.xormask

PYPI_PACKAGE = "websocket_client"
