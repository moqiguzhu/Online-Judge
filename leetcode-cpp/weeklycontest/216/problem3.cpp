#include <algorithm>
#include <deque>
#include <iostream>
#include <stack>
#include <unordered_map>
#include <vector>

using namespace std;

class Solution {
public:
  int waysToMakeFair(vector<int> &nums) {
    if (nums.size() == 0) {
      return 0;
    }
    if (nums.size() == 1) {
      return 1;
    }
    vector<pair<int, int>> left(nums.size());
    vector<pair<int, int>> right(nums.size());
    int t1, t2;
    for (int i = 0; i < nums.size(); i++) {
      if (i == 0) {
        t1 = 0;
        t2 = 0;
      } else {
        t1 = left[i - 1].second;
        t2 = left[i - 1].first;
      }
      if (i % 2 == 0) {
        left[i].second = t1 + nums[i];
        left[i].first = t2;
      } else {
        left[i].first = t2 + nums[i];
        left[i].second = t1;
      }
    }
    // for (auto kv : left) {
    //   cout << kv.first << "  " << kv.second.first << " " << kv.second.second
    //        << endl;
    // }

    for (int i = nums.size() - 1; i >= 0; i--) {
      if (i == nums.size() - 1) {
        t1 = 0;
        t2 = 0;
      } else {
        t1 = right[i + 1].second;
        t2 = right[i + 1].first;
      }
      if (i % 2 == 0) {
        right[i].second = t1 + nums[i];
        right[i].first = t2;
      } else {
        right[i].first = t2 + nums[i];
        right[i].second = t1;
      }
    }

    int res = 0;
    for (int i = 0; i < nums.size(); i++) {
      if (i == 0) {
        if (right[1].first == right[1].second) {
          res++;
        }
      } else if (i == nums.size() - 1) {
        if (left[i - 1].first == left[i - 1].second) {
          res++;
        }
      } else {
        if (left[i - 1].first + right[i + 1].second ==
            left[i - 1].second + right[i + 1].first) {
          res++;
        }
      }
    }

    return res;
  }
};

int main() {
  Solution s;
  vector<int> nums = {1, 2, 3};
  cout << s.waysToMakeFair(nums) << endl;
  return 0;
}