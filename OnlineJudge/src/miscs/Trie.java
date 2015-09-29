package miscs;

class TrieNode {
  // Initialize your data structure here.
  public TrieNode[] childs;
  public int pos;
  public TrieNode() {
      childs = new TrieNode[26];
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
  }

  // Returns if the word is in the trie.
  public boolean search(String word) {
      int index = 0;
      TrieNode tmp = root;
      while(index++ < word.length()) {
        int pos = Character.valueOf(word.charAt(index)) - 97;
        if(tmp.childs[pos] == null) {
          break;
        }
      }
      return false;
  }

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
      return false;
  }
  
//Your Trie object will be instantiated and called as such:
//Trie trie = new Trie();
//trie.insert("somestring");
//trie.search("key");
}
