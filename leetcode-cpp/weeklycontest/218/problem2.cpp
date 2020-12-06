#include <algorithm>
#include <deque>
#include <iostream>
#include <regex>
#include <stack>
#include <unordered_map>
#include <vector>

using namespace std;

bool compare(const pair<int, int> &e1, const pair<int, int> &e2) {
  return e1.second > e2.second;
}
class Solution {
public:
  int maxOperations(vector<int> &nums, int k) {
    unordered_map<int, int> num_cnt;
    for (auto num : nums) {
      num_cnt[num]++;
    }
    vector<pair<int, int>> numcnt;
    for (auto kv : num_cnt) {
      numcnt.push_back(kv);
    }
    sort(numcnt.begin(), numcnt.end(), compare);

    int res = 0;
    for (auto kv : numcnt) {
      if (num_cnt[kv.first] > 0 && num_cnt[k - kv.first] > 0) {
        if (kv.first * 2 == k) {
          res += kv.second / 2;
          num_cnt[kv.first] = 0;
        } else {
          res += num_cnt[k - kv.first];
          num_cnt[kv.first] = num_cnt[kv.first] - num_cnt[k - kv.first];
          num_cnt[k - kv.first] = 0;
        }
      }
    }

    return res;
  }
};

int main() {
  Solution s;
  vector<int> nums = {2, 2, 2, 3, 1, 1, 4, 1};
  int k = 4;
  cout << s.maxOperations(nums, k) << endl;
}