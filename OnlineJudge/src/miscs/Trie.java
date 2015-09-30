package miscs;

/**
 * You may assume that all inputs are consist of lowercase letters a-z.
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-09-30
 */

class TrieNode {
  // Initialize your data structure here.
  public TrieNode[] childs;
  public boolean isLeaf;

  public TrieNode() {
    childs = new TrieNode[26];
    isLeaf = false;
  }
}


public class Trie {
  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  // Inserts a word into the trie.
  public void insert(String word) {
    int index = 0;
    TrieNode tmp = root;
    for (index = 0; index < word.length(); index++) {
      int pos = Character.valueOf(word.charAt(index)) - 97;
      if (tmp.childs[pos] == null) {
        TrieNode node = new TrieNode();
        tmp.childs[pos] = node;
        tmp = node;
        continue;
      }
      tmp = tmp.childs[pos]; // word是已经存在Trie中某个单词的前缀
    }
    tmp.isLeaf = true;
  }

  // Returns if the word is in the trie.
  public boolean search(String word) {
    int index = 0;
    TrieNode tmp = root;
    while (index < word.length()) {
      int pos = Character.valueOf(word.charAt(index)) - 97;
      if (tmp.childs[pos] == null) {
        return false;
      }
      tmp = tmp.childs[pos];
      index++;
    }
    return tmp.isLeaf;
  }

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
    int index = 0;
    TrieNode tmp = root;
    while (index < prefix.length()) {
      int pos = Character.valueOf(prefix.charAt(index)) - 97;
      if (tmp.childs[pos] == null) {
        return false;
      }
      tmp = tmp.childs[pos];
      index++;
    }
    return true;
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insert("something");
    System.out.println(trie.search("some"));

    trie.insert("some");
    System.out.println(trie.search("some"));

    trie.insert("okay");
    System.out.println(trie.search("oka"));

    System.out.println(trie.startsWith("some"));
  }
}
