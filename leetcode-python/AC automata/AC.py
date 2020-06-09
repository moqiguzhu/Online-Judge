from collections import defaultdict
from collections import deque

# AC 自动机
# AC 自动机是一种字符串匹配方法
# 其加速字符串匹配过程的关键在于有suffix link和 output link
# AC 自动机适合的场景是pattern多且不变的case，时间复杂度？
# 构建过程分两步，第一步先构建字典树
# 第二部分，BFS遍历字典树，遍历的过程中添加两种link
# 具体过程是：根结点没有suffix link， 第一层节点的suffix link指向根节点
# 从第二层开始，每个节点的suffix link：找到节点v的上一个节点u， u到v的label为l，找到u的suffix link指向的节点p，如果p有一条label为l的边，那么u的suffix link就是从p开始，label为l的边指向的节点
# output link：如果一条suffix link指向终节点，那么这条suffix link也是一条output link
# 还有一种情况，虽然suffix link没有直接指向终节点，但是suffix link的suffix link指向终节点，那么此时需要增加一条当前节点到该终节点的output link


class ACNode():
    def __init__(self, suffix_link=None, output_link=None, reverse_link=None, is_final=False):
        self.children = defaultdict(ACNode)
        self.suffix_link = suffix_link
        self.output_link = output_link
        self.reverse_link = reverse_link
        self.is_final = is_final
        self.word = None


class AC(object):
    def __init__(self, words):
        self.sentinel = '#'
        self.root = ACNode()
        self.construct_trie(words)
        self.fast_suffix_output_link_BFS()

    def construct_trie(self, words):
        for w in words:
            self.__insert(w)

    def __insert(self, word):
        node = self.root
        l = len(word)
        for idx, w in enumerate(word):
            t = node
            node = node.children[w]
            # self.root没有reverse_link
            node.reverse_link = t
            if idx == l-1:
                node.is_final = True  # 设置终点标志
                node.word = word

    def fast_suffix_output_link_BFS(self):
        q = deque()
        q.append((self.sentinel, self.root))
        level = 0  # 0 means root
        while len(q) > 0:
            tmp = deque()
            for label, node in q:
                tmp.extend(node.children.items())
                # 设置suffix link
                if level == 0:
                    node.suffix_link = None
                elif level == 1:
                    node.suffix_link = self.root
                else:
                    t = node.reverse_link
                    while True:
                        if label in t.suffix_link.children:
                            node.suffix_link = t.suffix_link.children[label]
                            break
                        t = t.reverse_link
                        if t is self.root:
                            node.suffix_link = self.root
                            break
                # 设置output link
                if node.suffix_link is not None and node.suffix_link.is_final:
                    node.output_link = node.suffix_link
                elif node.suffix_link is not None and node.suffix_link.output_link is not None:
                    node.output_link = node.suffix_link.output_link
                else:
                    pass
            level += 1
            q = tmp

    def match(self, s):
        res = []
        if s is None or len(s) < 1:
            return res
        node = self.root
        for idx, w in enumerate(s):
            if w in node.children:
                node = node.children[w]
            else:
                while True:
                    if node is self.root:
                        break
                    node = node.suffix_link
                    if w in node.children:
                        break
            if node.is_final:
                res.append((idx - len(node.word)+1, node.word))
            t = node
            while t and t.output_link:
                res.append(
                    (idx - len(t.output_link.word)+1, t.output_link.word))
                t = t.output_link
        return res


words = ['i', 'in', 'tin', 'stin']
ac = AC(words)
print(ac.match('sting'))
