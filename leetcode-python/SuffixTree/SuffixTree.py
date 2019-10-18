# suffix tree实际上是一个字符串所有后缀组成的patricia trie，也叫做radix trie
# patricia trie有几个特点：
# 1. 如果字符串长度是m，叶子节点个数一定是m+1
# 2. 所有内部节点至少有两个子节点，除根结点外
# 多个字符串的后缀树组成的general suffix tree
# 首先每个字符串建立后缀树，然后跑一个DFS删除多余的后缀（没有研究到底如何实现）
