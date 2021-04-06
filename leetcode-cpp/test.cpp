#include <vector>
#include <iostream>
#include <stdint.h>

int test1(int x, int y = 0) {
    return x + y;
}

int main() {
    std::cout << test1(1) << std::endl;
    uint32_t x = 9999999999;
    std::cout << x << std::endl;
    std::cout << UINT32_MAX << std::endl;
    return 0; 
}

