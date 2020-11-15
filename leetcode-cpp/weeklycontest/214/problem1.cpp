#include <algorithm>
#include <deque>
#include <iostream>
#include <stack>
#include <vector>

using namespace std;

class Solution {
public:
  int getMaximumGenerated(int n) {
    vector<int> vec(n + 1);

    if (n < 1) {
      return 0;
    }
    vec[0] = 0;
    vec[1] = 1;
    int m = 1;
    for (int i = 2; i < n + 1; i++) {
      if (i % 2 == 0) {
        vec[i] = vec[i / 2];
      } else {
        vec[i] = vec[i / 2] + vec[i / 2 + 1];
      }
      m = max(vec[i], m);
    }
    return m;
  }
};

int main() {
  Solution s;
  cout << s.getMaximumGenerated(2) << endl;
}