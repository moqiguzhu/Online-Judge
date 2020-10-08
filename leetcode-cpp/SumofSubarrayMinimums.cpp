#include <iostream>
#include <vector>
#include <deque>
#include <algorithm>
#include<stack>

using namespace std;

class Solution
{
public:
    // 算法有问题
    int sumSubarrayMins1(vector<int> &A) {
        vector<int> left = {};
        vector<int> right = {};
        deque<int> dq = {};
        for(int e : A) {
            if(dq.empty()) {
                dq.push_back(e);
            } else {
                if(e >= dq.back()) {
                    dq.push_back(e);
                } else {
                    for(int i = dq.size(); i > 0; i--) {
                        left.push_back(i);
                    }
                    dq.clear();
                    dq.push_back(e);
                }
            }
        }
        if(!dq.empty()) {
            for(int i = dq.size(); i > 0; i--) {
                left.push_back(i);
            }
            dq.clear();
        }

        for(auto it = A.rbegin(); it != A.rend(); ++it) {
            int e = *it;
            // cout << e << endl;
            if(dq.empty()) {
                dq.push_back(e);
            } else {
                if(e > dq.back()) {
                    dq.push_back(e);
                } else {
                    for(int i = dq.size(); i > 0; i--) {
                        right.push_back(i);
                    }
                    dq.clear();
                    dq.push_back(e);
                }
            }
        }
        if(!dq.empty()) {
            for(int i = dq.size(); i > 0; i--) {
                right.push_back(i);
            }
            dq.clear();
        }
        // for(int e : right) {
        //     cout << e << endl;
        // }

        reverse(right.begin(), right.end());

        long res = 0;
        long M = pow(10, 9) + 7;
        for(int i = 0; i < left.size(); i++) {
            int e1 = left[i] - 1;
            int e2 = right[i] - 1;
            cout << e1 << "  " << e2 << endl;
            res += ((((e1 * e2) + e1 + e2 + 1) * A[i]) % M);
        }
        return res;
    }

    int sumSubarrayMins(vector<int> A) {
        int res = 0, n = A.size(), mod = 1e9 + 7;
        vector<int> left(n), right(n);
        stack<pair<int, int>> s1,s2;
        for (int i = 0; i < n; ++i) {
            int count = 1;
            while (!s1.empty() && s1.top().first > A[i]) {
                count += s1.top().second;
                s1.pop();
            }
            s1.push({A[i], count});
            left[i] = count;
        }
        for (int i = n - 1; i >= 0; --i) {
            int count = 1;
            while (!s2.empty() && s2.top().first >= A[i]) {
                count += s2.top().second;
                s2.pop();
            }
            s2.push({A[i], count});
            right[i] = count;
        }
        for (int i = 0; i < n; ++i)
            res = (res + A[i] * left[i] * right[i]) % mod;
        return res;
    }
};

int main()
{
    vector<int> A = {48,87,27};
    Solution *s = new Solution();
    int res = s->sumSubarrayMins(A);
    cout << res << endl;
    return 0;
}