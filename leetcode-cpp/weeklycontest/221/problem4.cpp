#include <algorithm>
#include <bitset>
#include <deque>
#include <iostream>
#include <map>
#include <regex>
#include <stack>
#include <unordered_map>
#include <vector>

using namespace std;

bool compare(const int &e1, const int &e2) { return e1 < e2; }

// 思路有了,代码写不出来
// debug
class Solution {
public:
  vector<int> maximizeXor(vector<int> &nums, vector<vector<int>> &queries) {
    sort(nums.begin(), nums.end(), compare);
    int x, m;
    vector<int> idx;
    vector<int> res;
    for (int k = 0; k <= 30; k++) {
      auto t = std::lower_bound(nums.begin(), nums.end(), 1 << k);
      idx.push_back(std::distance(nums.begin(), t));
    }
    // for (auto e : idx) {
    //   cout << e << endl;
    // }
    for (auto query : queries) {
      x = query[0];
      m = query[1];
      int left = 0;
      int right = std::distance(nums.begin(),
                                std::upper_bound(nums.begin(), nums.end(), m)) -
                  1;
      if (m < nums[0]) {
        res.push_back(-1);
        continue;
      }
      for (int k = 30; k >= 0; k--) {
        cout << left << " " << right << endl;
        if (left == right) {
          res.push_back(x ^ nums[left]);
          break;
        }
        if (x < (1 << k)) {
          if (idx[k] != nums.size()) {
            left = max(left, idx[k]);
          }
        } else {
          right = min(right, idx[k] - 1);
        }
      }
    }
    return res;
  }
};

int main() {
  Solution s;
  vector<int> nums = {0, 1, 2, 3, 4};
  vector<vector<int>> queries = {{3, 1}, {1, 3}, {5, 6}};
  auto res = s.maximizeXor(nums, queries);
  for (auto e : res) {
    cout << e << endl;
  }
}
