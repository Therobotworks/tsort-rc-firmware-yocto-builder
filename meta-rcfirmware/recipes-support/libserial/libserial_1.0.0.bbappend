do_install:append() {
    install -d ${D}${libdir}/pkgconfig
    cat > ${D}${libdir}/pkgconfig/libserial.pc << PCEOF
prefix=/usr
libdir=\${prefix}/lib
includedir=\${prefix}/include

Name: libserial
Description: Serial port library
Version: 1.0.0
Libs: -L\${libdir} -lserial
Cflags: -I\${includedir}
PCEOF
}

FILES:${PN}-dev += "${libdir}/pkgconfig/libserial.pc"
