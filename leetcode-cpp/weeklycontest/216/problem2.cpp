#include <algorithm>
#include <deque>
#include <iostream>
#include <stack>
#include <unordered_map>
#include <vector>

using namespace std;

class Solution {
public:
  string getSmallestString(int n, int k) {

    string res("");
    while (true) {
      if ((n - 1) * 26 >= k) {
        res += "a";
        n -= 1;
        k -= 1;
      } else {
        res += string(1, static_cast<char>(k - (n - 1) * 26 + 96));
        for (int i = 0; i < n - 1; i++) {
          res += "z";
        }
        break;
      }
    }
    return res;
  }
};

int main() {
  Solution s;
  int n = 3;
  int k = 27;
  cout << s.getSmallestString(n, k) << endl;
  return 0;
}