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

// tle 算法有问题
size_t key(int i, int j) { return i * 1000 + j; }
class Solution {
public:
  int stoneGameVII1(vector<int> &stones) {
    map<int, int> dp;
    map<int, int> dp1;
    int r = 0, k = 0;
    for (int len = 1; len <= stones.size(); len++) {
      for (int l = 0; l + len <= stones.size(); l++) {
        r = l + len - 1;
        k = key(l, r);
        if (len > 2) {
          //   dp[k] = max(dp1[key(l, r - 1)] - dp[key(l, r - 1)],
          //               dp1[key(l + 1, r)] - dp[key(l + 1, r)]);
          dp[k] = max(dp1[l * 1000 + r - 1] - dp[l * 1000 + r - 1],
                      dp1[(l + 1) * 1000 + r] - dp[(l + 1) * 1000 + r]);
          dp1[k] = dp1[key(l, r - 1)] + stones[r];
        } else if (len == 2) {
          dp[k] = max(stones[l], stones[r]);
          dp1[k] = stones[l] + stones[r];
        } else {
          dp[k] = 0;
          dp1[k] = stones[l];
        }
      }
    }
    // for (auto kv : dp) {
    //   cout << kv.first << " " << kv.second << endl;
    // }
    return dp[key(0, stones.size() - 1)];
  }

  // 能用数组不用map
  int stoneGameVII(vector<int> &stones) {
    int dp[1001][1001];
    int dp1[1001][1001];
    int r = 0, k = 0;
    for (int len = 1; len <= stones.size(); len++) {
      for (int l = 0; l + len <= stones.size(); l++) {
        r = l + len - 1;
        if (len > 1) {
          //   dp[k] = max(dp1[key(l, r - 1)] - dp[key(l, r - 1)],
          //               dp1[key(l + 1, r)] - dp[key(l + 1, r)]);
          dp[l][r] =
              max(dp1[l][r - 1] - dp[l][r - 1], dp1[l + 1][r] - dp[l + 1][r]);
          dp1[l][r] = dp1[l][r - 1] + stones[r];
        } else {
          dp[l][r] = 0;
          dp1[l][r] = stones[l];
        }
      }
    }
    // for (auto kv : dp) {
    //   cout << kv.first << " " << kv.second << endl;
    // }
    return dp[0][stones.size() - 1];
  }
};

int main() {
  Solution s;
  vector<int> stones = {5, 3, 1, 4, 2};
  cout << s.stoneGameVII(stones) << endl;
}