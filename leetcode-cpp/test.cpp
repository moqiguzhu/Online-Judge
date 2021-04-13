#include <vector>
#include <iostream>
#include <stdint.h>
#include <string>

using namespace std;

int test1(int x, int y = 0) {
    return x + y;
}

int main() {
    std::cout << test1(1) << std::endl;
    uint32_t x = 9999999999;
    std::cout << x << std::endl;
    std::cout << UINT32_MAX << std::endl;

    std::string s = "and xxx";
    cout << s.substr(3) << endl;
    cout << (s.substr(0,3) == "and") << endl;

    return 0; 
}

