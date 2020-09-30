#include <vector>
#include <iostream>

using namespace std;

class Solution
{
public:
    int sumSubarrayMins(vector<int> &A)
    {
        int i = 0, j = 0;
        int len = A.size();
        int k = 0;
        vector<int> left = {};
        vector<int> right = {};
        for (k = 0; k < len; k++)
        {
            if (k > 0 && A[k] < A[k - 1])
            {
                int p;
                for (p = i - 1; p >= 0; p--)
                {
                    if (A[k] >= A[p])
                    {
                        break;
                    }
                }
                if (p < 0)
                {
                    left.push_back(p + 1);
                }
                else
                {
                    left.push_back(p);
                }

                i = p;

                for (p = j + 1; p < len; p++)
                {
                    if (A[k] < A[p])
                    {
                        break;
                    }
                }
                if (p >= len)
                {
                    right.push_back(p - 1);
                }
                else
                {
                    right.push_back(p);
                }
                j = p;
            }
            else
            {
                left.push_back(k);
                i = k;
                int p;
                for (p = k; p < len; p++)
                {
                    if (A[p] < A[k])
                    {
                        break;
                    }
                }
                if (p >= len)
                {
                    right.push_back(p - 1);
                }
                else
                {
                    right.push_back(p);
                }
                j = p;
            }
        }
        return 0;
    }

    long calcCombinations(int n, int r)
    {
        long res = 1;
        int i, j;
        long M = pow(10, 9) + 7;
        for (i = n, j = 1; j <= r; i--, j++)
        {
            res = (res * i / j) % M;
        }
        return res;
    }
};

int main()
{
    vector<int> A = {3, 1, 2, 4};
    Solution *s = new Solution();
    int res = s->sumSubarrayMins(A);
    cout << res << endl;
    return 0;
}