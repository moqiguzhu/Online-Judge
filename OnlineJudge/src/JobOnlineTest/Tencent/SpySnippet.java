package test;

/**
 * Spy snippets
============
You've been recruited by the team building Spy4Rabbits, a highly advanced search engine used to help fellow agents discover files and intel needed to continue the operations against Dr. Boolean's evil experiments. The team is known for recruiting only the brightest rabbit engineers, so there's no surprise they brought you on board. While you're elbow deep in some important encryption algorithm, a high-ranking rabbit official requests a nice aesthetic feature for the tool called "Snippet Search." While you really wanted to tell him how such a feature is a waste of time in this intense, fast-paced spy organization, you also wouldn't mind getting kudos from a leader. How hard could it be, anyway?
When someone makes a search, Spy4Rabbits shows the title of the page. Your commander would also like it to show a short snippet of the page containing the terms that were searched for.
Write a function called answer(document, searchTerms) which returns the shortest snippet of the document, containing all of the given search terms. The search terms can appear in any order.
The length of a snippet is the number of words in the snippet. For example, the length of the snippet "tastiest color of carrot" is 4. (Who doesn't like a delicious snack!)
The document will be a string consisting only of lower-case letters [a-z] and spaces. Words in the string will be separated by a single space. A word could appear multiple times in the document.
searchTerms will be a list of words, each word comprised only of lower-case letters [a-z]. All the search terms will be distinct.
Search terms must match words exactly, so "hop" does not match "hopping".
Return the first sub-string if multiple sub-strings are shortest. For example, if the document is "world there hello hello where world" and the search terms are ["hello", "world"], you must return "world there hello".
The document will be guaranteed to contain all the search terms.
The number of words in the document will be at least one, will not exceed 500, and each word will be 1 to 10 letters long. Repeat words in the document are considered distinct for counting purposes.
The number of words in searchTerms will be at least one, will not exceed 100, and each word will not be more than 10 letters long.
Languages
=========
To provide a Python solution, edit solution.py
To provide a Java solution, edit solution.java
Test cases
==========
Inputs:
    (string) document = "many google employees can program"
    (string list) searchTerms = ["google", "program"]
Output:
    (string) "google employees can program"
Inputs:
    (string) document = "a b c d a"
    (string list) searchTerms = ["a", "c", "d"]
Output:
    (string) "c d a"
 * @author moqiguzhu
 * 有一个字符串经过了加密处理，加密的方式是不断向原始字符串里面插入某个单词word。
 * 现在已知加密之后的字符串，和用来加密的单词，求原始的字符串，还已知原始的字符串是所有可能的字符串里面字母序最靠前的。
 */
public class SpySnippet {
	  public static String answer(String chunk, String word) {
	    String[] candidates = new String[chunk.length()+1];
	    
	    for(int i = 1; i < word.length(); i++) {
	      candidates[i] = chunk.substring(0,i);
	    }
	    
	    if(chunk.substring(0,word.length()).equals(word)) {
	      candidates[word.length()] = "";
	    } else {
	      candidates[word.length()] = chunk.substring(0,word.length());
	    }
	    
	    for(int i = word.length()+1; i <= chunk.length(); i++) {
	      String tmp1 = chunk.substring(i-word.length(),i);
	      if(tmp1.equals(word)) {
	        tmp1 = "";
	      }
	      String res1 = candidates[i-1] + chunk.charAt(i-1);
	      String res2 = candidates[i-word.length()] + tmp1;
	      candidates[i] = strMin(res1,res2);
	    }
	    
	    String curRes = candidates[candidates.length-1];
	    if(!curRes.contains(word)) {
	      return candidates[candidates.length-1];
	    } else {
	      return answer(curRes, word);
	    }
	    
	  }
	  
	  public static String strMin(String res1, String res2) {
	    if(res1.length() < res2.length()) {
	      return res1;
	    } else if(res1.length() > res2.length()) {
	      return res2;
	    } else {
	      if(res1.compareTo(res2) < 0) {
	        return res1;
	      } else {
	        return res2;
	      }
	    }
	  }
	  
	  public static void main(String[] args) {
	    String chunk = "aaaaaaaaaaaaaaaaaaaa";
	    String word = "aaaaaaa";
	    System.out.println(answer(chunk, word));
	  }
	}