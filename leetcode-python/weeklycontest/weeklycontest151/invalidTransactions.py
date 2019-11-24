from typing import List
from collections import defaultdict


class Solution:
    def invalidTransactions(self, transactions: List[str]) -> List[str]:
        d = defaultdict(lambda: [])
        res = set()
        for e in transactions:
            t = e.split(',')
            if int(t[2]) > 1000:
                res.add(e)

            if t[0] not in d:
                pass
            else:
                for k in d[t[0]]:
                    if k[2] != t[3] and abs(int(k[0]) - int(t[1])) <= 60:
                        res.add(','.join([t[0], k[0], k[1], k[2]]))
                        res.add(e)
            d[t[0]].append((t[1], t[2], t[3]))
        return res


s = Solution()
transactions = ["bob,627,1973,amsterdam", "alex,387,885,bangkok", "alex,355,1029,barcelona",
                "alex,587,402,bangkok", "chalicefy,973,830,barcelona", "alex,932,86,bangkok", "bob,188,989,amsterdam"]
print(s.invalidTransactions(transactions))
