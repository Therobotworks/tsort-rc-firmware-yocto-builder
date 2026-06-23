SUMMARY = "ZXing-C++ barcode reader"
HOMEPAGE = "https://github.com/zxing-cpp/zxing-cpp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fa818a259cbed7ce8bc2a22d35a464fc"

SRC_URI = "gitsm://github.com/zxing-cpp/zxing-cpp.git;protocol=https;branch=master"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = "-DZXING_EXAMPLES=OFF -DZXING_UNIT_TESTS=OFF -DZXING_BLACKBOX_TESTS=OFF -DZXING_C_API=OFF"

FILES:${PN} = "${libdir}/libZXing.so.*"
FILES:${PN}-dev = "${includedir}/ZXing/* ${libdir}/libZXing.so ${libdir}/cmake/ZXing/* ${libdir}/pkgconfig/zxing.pc"
