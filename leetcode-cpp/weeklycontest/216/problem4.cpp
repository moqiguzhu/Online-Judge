#include <algorithm>
#include <deque>
#include <iostream>
#include <stack>
#include <unordered_map>
#include <vector>

using namespace std;

bool compare(const vector<int> &e1, const vector<int> &e2) {
  return max(e1[0] + e2[1], e1[1]) < max(e1[1] + e2[0], e2[1]);
}
class Solution {
public:
  int minimumEffort(vector<vector<int>> &tasks) {
    sort(tasks.begin(), tasks.end(), compare);
    int res = 0;
    int cur = 0;
    // for (auto task : tasks) {
    //   cout << task[0] << " " << task[1] << endl;
    // }
    for (auto task : tasks) {
      if (cur < task[1]) {
        res += (task[1] - cur);
        cur = task[1];
      }
      cur = cur - task[0];
    }
    return res;
  }
};

int main() {
  Solution s;
  // vector<vector<int>> tasks = {{1, 3}, {2, 4}, {10, 11}, {10, 12}, {8, 9}};
  vector<vector<int>> tasks = {{1, 7},  {2, 8},  {3, 9},
                               {4, 10}, {5, 11}, {6, 12}};
  cout << s.minimumEffort(tasks) << endl;
  return 0;
}