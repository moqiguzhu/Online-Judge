from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest


def problem1(file_path):
    wxid_cnt = defaultdict(lambda x: 0)
    with open(file_path, encoding='utf8') as f:
        for line in f.readlines():
            wxid = line.strip()
            wxid_cnt[wxid] += 1
    # 边界
    if len(wxid_cnt) == 0:
        print('文件为空')
        return ''

    c = Counter(wxid_cnt)
    mostapp_wxid, freq = c.most_common(1)

    return mostapp_wxid


class TreeNode:
    def __init__(self, val):
        self.val = x
        self.left = None
        self.right = None


def problem2(root):
    pass
