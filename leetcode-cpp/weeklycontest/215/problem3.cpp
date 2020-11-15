#include <algorithm>
#include <deque>
#include <iostream>
#include <stack>
#include <unordered_map>
#include <vector>
using namespace std;

// tle
class Solution {
public:
  // 层次遍历 不用递归
  int minOperations1(vector<int> &nums, int x) {
    // 处理边界
    if (x == 0) {
      return 0;
    }
    if (nums.size() == 1) {
      if (nums[0] == x) {
        return 1;
      } else {
        return -1;
      }
    }

    long sum = 0;
    for (auto num : nums) {
      sum += num;
    }
    if (sum == x) {
      return nums.size();
    }

    int level = 1;
    int n = nums.size();
    unordered_map<int, pair<int, int>> left_right_x;
    left_right_x[0] = make_pair(n - 2, x - nums[n - 1]);
    left_right_x[1] = make_pair(n - 1, x - nums[0]);

    unordered_map<int, pair<int, int>> t;
    while (left_right_x.size() > 0) {
      for (auto kv : left_right_x) {
        if (kv.second.second == 0) {
          return level;
        } else {
          if (kv.first <= kv.second.first) {
            t[kv.first] = make_pair(kv.second.first - 1,
                                    kv.second.second - nums[kv.second.first]);
          }
          if (kv.first + 1 <= kv.second.first + 1) { // 最后一个元素都要考虑进来
            t[kv.first + 1] =
                make_pair(kv.second.first, kv.second.second - nums[kv.first]);
          }
        }
      }
      left_right_x.clear();
      left_right_x = t;
      t.clear();

      level++;
    }

    return -1;
  }

  int minOperations(vector<int> &nums, int x) {
    unordered_map<int, int> left, right;
    int cur_sum = 0;
    for (int i = 0; i < nums.size(); i++) {
      cur_sum += nums[i];
      left[cur_sum] = i;
    }
    left[0] = -1;

    cur_sum = 0;
    for (int i = nums.size() - 1; i >= 0; i--) {
      cur_sum += nums[i];
      right[cur_sum] = i;
    }
    right[0] = nums.size();

    int res = 1000000009;
    for (auto kv : left) {
      if (right.find(x - kv.first) != right.end() &&
          right[x - kv.first] > kv.second) { // 用find实现
        res =
            min(res, int(kv.second + 1 + (nums.size() - right[x - kv.first])));
      }
    }
    return res == 1000000009 ? -1 : res;
  }
};

int main() {
  Solution s;
  vector<int> nums = {1, 1};
  int x = 3;
  cout << s.minOperations(nums, x) << endl;
}