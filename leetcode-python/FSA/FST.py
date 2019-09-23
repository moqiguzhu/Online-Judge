import timeit
from collections import deque
from collections import defaultdict
import random
import string
import sys
import time

try:
    from Trie import Trie
except:
    sys.path.append('..')
    from Trie import Trie


class STATE(object):
    def __init__(self, no):
        self.children = defaultdict(lambda x: x)
        self.no = no
        self.signature = ''
        self.last_child = None
        self.last_label = None

    def add_children(self, label, weight, child):
        self.children[label] = (child, weight)
        # 在某些特定情况下，这个 sig 会导致 bug 比如说：
        # strs = ['Abadite', 'Abderian', 'Abderite', 'Abdiel']
        # self.signature = str(label) + ',' + str(child.no)

        # 最笨的signature 但是不会出错
        self.signature = ','.join([str(v)+','+k
                                   for k, v in self.children.items()])
        self.last_child = child
        self.last_label = label

    def get_child(self, label):
        return self.children[label]

    def remove_child(self, label, equi_state):
        # 删除节点的时候 weight不变
        weight = self.children[label][1]
        self.children[label] = (equi_state, weight)
        self.last_child = equi_state
        self.signature = ','.join([str(v)+','+k
                                   for k, v in self.children.items()])

    def has_children(self):
        return len(self.children) > 0


def key_common(key1, key2):
    return key1 if key1 < key2 else key2


def key_substract(key1, key2):
    return key1 - key2


def key_add(key1, key2):
    return key1 + key2


class FST(object):
    # strs must be sorted
    def __init__(self, strs=[], values=[]):
        self.register_states = defaultdict(lambda x: x)
        self.root = STATE(0)
        self.final = None
        self.id = 1

        last_s = ''
        for i, s in enumerate(strs):
            commmon_prefix, last_state, cur_suffix = '', self.root, ''
            remain = values[i]
            # 权重在求common prefix的时候就已经分配好了
            for idx, c in enumerate(s):
                if idx < len(last_s) and last_s[idx] == c:
                    commmon_prefix += c
                    common = key_common(remain, last_state.get_child(c)[1])
                    over = key_substract(last_state.get_child(c)[1], common)

                    for k, v in last_state.get_child(c)[0].children.items():
                        last_state.get_child(c)[0].children[k] = (
                            v[0], key_add(v[1], over))
                    last_state.children[c] = (
                        last_state.children[c][0], common)
                    remain = key_substract(remain, last_state.get_child(c)[1])
                    last_state = last_state.get_child(c)[0]
                else:
                    cur_suffix = s[idx:]
                    break
            if last_state.has_children():
                self.replace_or_register(last_state)
            self.add_suffix(last_state, cur_suffix, remain)
            last_s = s
            if i == len(strs)-1:
                # finish
                self.replace_or_register(last_state)

    def add_suffix(self, state, suffix, remain):
        child = None
        for c in suffix:
            child = STATE(self.id)
            self.id += 1
            state.add_children(c, remain, child)
            remain = 0
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
            sig = state.get_child(state.last_label)[0].signature
        if sig in self.register_states:
            state.remove_child(state.last_label, self.register_states[sig])
        else:
            self.register_states[sig] = child
        # print(self.register_states)

    def actual_state_count(self):
        return len(self.register_states)

    def contains(self, s):
        cur_state = self.root
        for c in s:
            if c not in cur_state.children:
                return False
            cur_state = cur_state.children[c][0]
        return True


def print_fst(st, cur_str, cur_no_str):
    if not st[-1][0].has_children():
        print(cur_str)
        # print('0' + cur_no_str)  # 所有路径从root开始

    for k, v in st[-1][0].children.items():
        t = st + [v]
        print_fst(t, cur_str + k + '/' +
                  str(v[1]) + ',', cur_no_str + str(v[0].no))


def trie_node_count(cur_node):
    t = 0
    for k, v in cur_node.children.items():
        t += trie_node_count(v)
    return t + len(cur_node.children)


# strs = ['mon', 'thurs', 'tues', 'tye', 'tyx']
# values = [2, 5, 3, 99, 1]
# fst = FST(strs, values)
# print_fst([(fst.root, -1)], '', '')

# strs = ['Abderian']
# values = [0]
# fst = FST(strs, values)
# print_fst([(fst.root, -1)], '', '')
# print(fst.contains('Abderian'))


def randomString(stringLength=10):
    """Generate a random string of fixed length """
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(stringLength))


if __name__ == "__main__":
    num_words = 10000
    min_len, max_len = 5, 100
    strs = []
    values = []
    total_state_count = 0
    # 随机生成的字符串测试不合理 基本没有压缩率
    # for i in range(num_words):
    #     cur_len = random.randrange(min_len, max_len+1)
    #     cur_word = randomString(cur_len)
    #     strs.append(cur_word)
    #     values.append(0)
    #     total_state_count += len(cur_word)
    with open('/usr/share/dict/words', encoding='utf8') as f:
        for line in f.readlines():
            line = line.strip()
            strs.append(line)
            values.append(0)
            total_state_count += len(line)
    strs = sorted(strs)
    fst = FST(strs, values)
    actual_state_count = fst.actual_state_count()

    trie = Trie(strs)
    trie_node_count = trie_node_count(trie.root)
    print('Trie letters counting: %s' % (str(trie_node_count)))

    p_d = {}
    for s in strs:
        p_d[s] = 0

    start = time.process_time()
    t1 = [fst.contains(s) for s in strs]
    gap1 = time.process_time() - start
    start = time.process_time()
    t2 = [trie.search(s) for s in strs]
    gap2 = time.process_time() - start
    start = time.process_time()
    t3 = [s in p_d for s in strs]
    gap3 = time.process_time() - start

    print('Space comparison with Python dict, Trie and FST')
    print('total letters counting for Python dict: %s' %
          (str(total_state_count)))
    print('total letters counting for Trie: %s' % (str(trie_node_count)))
    print('total letters counting for FST: %s' % (str(actual_state_count)))

    print("\n#######################################################\n")
    print('Time comparison with Python dict, Trie and FST')
    print('average search time for Python dict: %s ns' %
          (str(int(gap3 / len(strs) * 10**9))))
    print('average search time for Trie: %s ns' %
          (str(int(gap2 / len(strs) * 10**9))))
    print('average search time for FST: %s ns' %
          (str(int(gap1 / len(strs) * 10**9))))

    # 进一步判断构造的FST是不是正确
    # count = 0
    # for s in strs:
    #     if not fst.contains(s):
    #         print(s)
    #         count += 1
