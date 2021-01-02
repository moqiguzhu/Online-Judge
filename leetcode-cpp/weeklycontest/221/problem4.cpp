#include <algorithm>
#include <bitset>
#include <deque>
#include <iostream>
#include <map>
#include <regex>
#include <set>
#include <stack>
#include <unordered_map>
#include <vector>
using namespace std;

bool compare(const int &e1, const int &e2) { return e1 < e2; }

// tle
class Solution {
 public:
  vector<int> maximizeXor(vector<int> &nums,
                           vector<vector<int>> &queries) {  // 这个实现好蠢
    sort(nums.begin(), nums.end(), compare);
    int x, m;
    vector<int> idx;  // 这里设计有问题，正确的设计是一棵字典树
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
        // cout << left << " " << right << endl;
        if (left == right) {
          break;
        }
        if (idx[k] <= right && idx[k] >= left && idx[k] - 1 >= left) {
          // cout << (x & (1 << k)) << endl;
          if ((x & (1 << k)) == 0) {
            left = max(left, idx[k]);
          } else {
            right = min(right, idx[k] - 1);
          }
        }
      }
      int t = 0;
      for (int i = left; i <= right; i++) {
        t = max(t, x ^ nums[i]);
      }
      res.push_back(t);
    }
    return res;
  }
};

int main() {
  Solution s;
  vector<int> nums = {245405671, 478954451, 192656583, 756912244, 536870912,
                      536870912, 632641004, 536870912, 536870912, 885997335,
                      66102331,  96363044,  456847995, 969641794, 56056493,
                      929047056, 239297896, 536870912, 36418831,  394600426};
  vector<vector<int>> queries = {{345073691, 1000000000}};
  auto res = s.maximizeXor(nums, queries);
  for (auto e : res) {
    cout << e << endl;
  }
}
