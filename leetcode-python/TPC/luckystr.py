import fileinput
import numpy as np
from collections import defaultdict


def help1(s):
    if s is None or len(s) < 3:
        return 'impossible'
    char_idx = defaultdict(set)
    for idx, c in enumerate(s):
        if c in ['T', 'P', 'C']:
            char_idx[c].add(idx)
    res = []
    for t in ['TPC', 'TCP', 'PTC', 'PCT', 'CPT', 'CTP']:
        res.extend(help2(t, char_idx))

    tbl = {
        'TPC': [0, 1, 2, 1],
        'TCP': [1, 2, 2, 1],
        'PTC': [1, 2, 3, 3],
        'PCT': [2, 2, 3, 3],
        'CPT': [1, 2, 3, 2],
        'CTP': [2, 3, 3, 3],
    }
    tbl = sum(tbl.values(), [])

    res = min([tbl[idx] if res[idx] == 1 else 10 **
               9+7 for idx, e in enumerate(res)])
    if res == 10**9+7:
        return 'Impossible'
    else:
        return res


def help2(t, char_idx):
    t1, t2, t3 = t
    res = [0, 0, 0, 0]

    for e in char_idx[t1]:
        if e+1 in char_idx[t2] and e+2 in char_idx[t3]:
            res[0] = 1
        if e+1 in char_idx[t2] and max(char_idx[t3]) > e+1:
            res[1] = 1
        if len([x for x in char_idx[t2] if x > e+1]) > 0 and max(char_idx[t3]) > min([x for x in char_idx[t2] if x > e+1]) + 1:
            res[2] = 1
        for x in char_idx[t2]:
            if x > e+1 and x+1 in char_idx[t3]:
                res[3] = 1
    return res


if __name__ == '__main__':
    T = 0
    index = 0
    strs = []
    for line in fileinput.input():
        if index == 0:
            T = int(line.strip())
        elif index % 2 == 1:
            pass
        else:
            s = line.strip()
            strs.append(s)
        index += 1
        if index == 2 * T + 1:
            break
    for s in strs:
        print(help1(s))
