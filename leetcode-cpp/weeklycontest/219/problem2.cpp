#include <algorithm>
#include <bitset>
#include <deque>
#include <iostream>
#include <regex>
#include <stack>
#include <unordered_map>
#include <vector>

using namespace std;

class Solution {
public:
  int minPartitions(string n) {
    char res = '0';
    for (auto c : n) {
      res = max(c, res);
    }

    return res - '0';
  }
};

int main() {
  Solution s;
  string n = "82734";
  cout << s.minPartitions(n) << endl;
}