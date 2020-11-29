#include <algorithm>
#include <deque>
#include <iostream>
#include <stack>
#include <vector>

using namespace std;

class Solution {
public:
  int maximumWealth(vector<vector<int>> &accounts) {
    long res = 0;
    long t;
    for (size_t i = 0; i < accounts.size(); i++) {
      t = 0;
      for (size_t j = 0; j < accounts[i].size(); j++) {
        t += accounts[i][j];
      }
      res = max(res, t);
    }
    return res;
  }
};

int main() {
  Solution s;
  vector<vector<int>> accounts = {{1, 5}, {7, 3}, {3, 5}};
  cout << s.maximumWealth(accounts) << endl;
}