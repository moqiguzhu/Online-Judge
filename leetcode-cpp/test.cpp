#include <vector>
#include <iostream>
#include <stdint.h>
#include <string>
#include <time.h>
#include <ctime>

using namespace std;

int test1(int x, int y = 0) {
    return x + y;
}

int test2() {
    std::string s = "20211223";
    s = s.substr(0, 6) + "01";
    cout << s << endl;
    struct tm tm;
    strptime(s.c_str(), "%Y%m%d", &tm);
    
    time_t ts = mktime(&tm);
    cout << ts << endl;
    return 0;
}

int main() {
    // std::cout << test1(1) << std::endl;
    // uint32_t x = 9999999999;
    // std::cout << x << std::endl;
    // std::cout << UINT32_MAX << std::endl;

    // std::string s = "and xxx";
    // cout << s.substr(3) << endl;
    // cout << (s.substr(0,3) == "and") << endl;

    test2();

    return 0; 
}