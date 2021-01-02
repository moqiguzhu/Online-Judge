#include <iostream>
#include <unordered_map>
#include <vector>

using namespace std;
class Solution {
 public:
  bool canFormArray(vector<int> &arr, vector<vector<int>> &pieces) {
    unordered_map<int, int> num_idx;
    for (unsigned int i = 0; i < arr.size(); i++) {
      num_idx[arr[i]] = i;
    }
    int idx;
    for (auto p : pieces) {
      for (unsigned int i = 0; i < p.size(); i++) {
        if (i == 0) {
          if (num_idx.find(p[i]) == num_idx.end()) {
            return false;
          } else {
            idx = num_idx[p[i]];
          }
        } else {
          if (arr[idx] != p[i]) {
            return false;
          }
        }
        idx = idx + 1;
      }
    }
    return true;
  }
};

int main() {
  Solution s;
  vector<int> arr = {1, 3, 5, 7};
  vector<vector<int>> pieces = {{2, 4, 6, 8}};
  cout << s.canFormArray(arr, pieces) << endl;
}
