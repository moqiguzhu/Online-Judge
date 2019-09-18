from collections import defaultdict
from collections import deque


class STATE(object):
    def __init__(self, no):
        self.children = defaultdict(lambda x: x)
        self.no = no
        self.signature = ''
        self.last_child = None
        self.last_label = None

    def add_children(self, label, child):
        self.children[label] = child
        # 为什么这个sig能够work？能不能work？
        self.signature = str(label) + ',' + str(child.no)
        self.last_child = child
        self.last_label = label

    def get_child(self, label):
        return self.children[label]

    def remove_child(self, label, equi_state):
        self.children[label] = equi_state
        self.last_child = equi_state
        self.signature = str(label) + \
            ',' + str(equi_state.no)

    def has_children(self):
        return len(self.children) > 0


class FSA(object):
    # strs must be sorted
    def __init__(self, strs=[]):
        self.register_states = defaultdict(lambda x: x)
        self.root = STATE(0)
        self.final = None
        self.id = 1

        last_s = ''
        for i, s in enumerate(strs):
            commmon_prefix, last_state, cur_suffix = '', self.root, ''
            for idx, c in enumerate(s):
                if idx < len(last_s) and last_s[idx] == c:
                    commmon_prefix += c
                    last_state = last_state.get_child(c)
                else:
                    cur_suffix = s[idx:]
                    break
            if last_state.has_children():
                self.replace_or_register(last_state)
            self.add_suffix(last_state, cur_suffix)
            last_s = s
            if i == len(strs)-1:
                # finish
                self.replace_or_register(last_state)

    def add_suffix(self, state, suffix):
        child = None
        for c in suffix:
            child = STATE(self.id)
            self.id += 1
            state.add_children(c, child)
            state = child
        # final为第一个词的最后一个状态
        if self.final is None:
            self.final = child
            # 因为final没有出度，sig仅仅由no组成
            self.register_states[str(self.final.no)] = self.final

    def replace_or_register(self, state):
        child = state.last_child
        if child.has_children():
            self.replace_or_register(child)
        sig = ''
        if not child.has_children():
            state.remove_child(state.last_label, self.final)
            return
        else:
            sig = str(child.last_label) + \
                ',' + str(child.last_child.no)
        if sig in self.register_states:
            state.remove_child(state.last_label, self.register_states[sig])
        else:
            self.register_states[sig] = child
        # print(self.register_states)


def print_fsa(st, cur_str, cur_no_str):
    if not st[-1].has_children():
        print(cur_str)
        print('0' + cur_no_str)  # 所有路径从root开始
    for k, v in st[-1].children.items():
        t = st + [v]
        print_fsa(t, cur_str + k, cur_no_str + str(v.no))


# 状态8是不是还存在
strs = ['mon', 'thurs', 'tues', 'ttt', 'xxx', 'yyy']
fsa = FSA(strs)
print_fsa([fsa.root], '', '')
