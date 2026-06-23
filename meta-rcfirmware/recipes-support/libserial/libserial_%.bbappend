# Dynamically inject the missing header right before the configuration step begins
do_configure:prepend() {
    # Insert #include <cstdint> at the very first line (1i) of the file
    sed -i '1i #include <cstdint>' ${S}/src/libserial/SerialPortConstants.h
}
