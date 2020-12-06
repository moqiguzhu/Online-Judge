#include <algorithm>
#include <bitset>
#include <deque>
#include <iostream>
#include <regex>
#include <stack>
#include <unordered_map>
#include <vector>

using namespace std;

vector<long> cache = {0};
class Solution {
public:
  int concatenatedBinary(int n) {
    int M = pow(10, 9) + 7;
    if (cache.size() > n) {
      return cache[n];
    }

    std::string bs;
    int bitcnt;
    for (int i = cache.size(); i <= n; i++) {
      bs = std::bitset<32>(i).to_string();
      bitcnt = 32 - 1 - bs.find("01", 0);
      cache.push_back((cache[i - 1] * (long)pow(2, bitcnt)) % M + i);
    }
    return cache[n];
  }
};

int main() {
  Solution s;
  int n = 12;
  cout << s.concatenatedBinary(n) << endl;
}