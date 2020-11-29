#include <algorithm>
#include <deque>
#include <iostream>
#include <numeric>
#include <stack>
#include <unordered_map>
#include <vector>

using namespace std;

bool compare(const pair<int, int> &e1, const pair<int, int> &e2) {
  return e1.first < e2.first || (e1.first == e2.first && e1.second < e2.second);
}

bool compare1(const pair<int, int> &e1, const pair<int, int> &e2) {
  return e1.first < e2.first;
}

class Solution {
public:
  // 想多了
  vector<int> mostCompetitive1(vector<int> &nums, int k) {
    vector<int> idx(nums.size());
    iota(idx.begin(), idx.end(), 0);
    // for (auto e : idx) {
    //   cout << e << " ";
    // }
    // cout << endl;

    sort(idx.begin(), idx.end(),
         [&nums](int i1, int i2) { return nums[i1] < nums[i2]; });
    // for (auto e : idx) {
    //   cout << e << " ";
    // }
    // cout << endl;
    vector<pair<int, int>> num_idx(nums.size());
    for (int i = 0; i < nums.size(); i++) {
      num_idx[i] = make_pair(nums[i], idx[i]);
    }
    stable_sort(num_idx.begin(), num_idx.end(), compare);

    // for (auto e : num_idx) {
    //   cout << e.first << "," << e.second << " ";
    // }
    // cout << endl;
    vector<int> res;

    for (auto e : num_idx) {
      if (res.size() == k) {
        break;
      }
      if (nums.size() - e.second >= k - res.size()) {
        res.push_back(e.first);
      }
    }
    return res;
  }
  vector<int> mostCompetitive(vector<int> &nums, int k) {
    vector<pair<int, int>> num_idx;
    for (int i = 0; i < nums.size(); i++) {
      num_idx.push_back(make_pair(nums[i], i));
    }
    // stable_sort(num_idx.begin(), num_idx.end(), [&num_idx](int i1, int i2) {
    //   return num_idx[i1].first < num_idx[i2].first;
    // });
    stable_sort(num_idx.begin(), num_idx.end(), compare1);
    vector<int> res;

    for (auto e : num_idx) {
      if (res.size() == k) {
        break;
      }
      if (nums.size() - e.second >= k - res.size()) {
        res.push_back(e.first);
      }
    }
    return res;
  }
};

int main() {
  Solution s;
  vector<int> nums = {2, 4, 3, 3, 5, 4, 9, 6};
  int k = 4;
  for (auto e : s.mostCompetitive(nums, k)) {
    cout << e << " ";
  }
  cout << endl;
}